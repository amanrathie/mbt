package br.gov.cgu.mbt.web.auth;

import br.gov.cgu.mbt.aplicacao.auth.BuscadorDeUsuario;
import br.gov.cgu.mbt.aplicacao.auth.UsuarioDTO;
import br.gov.cgu.mbt.negocio.auth.Usuario;
import br.gov.cgu.mbt.web.ControllerAdvice;
import br.gov.cgu.mbt.web.auth.BuscarUsuarioController;
import br.gov.cgu.persistencia.jpa.EntidadeInvalidaException;
import br.gov.cgu.persistencia.querybuilder.RespostaConsulta;
import br.gov.cgu.test.mvc.ControllerTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class BuscarUsuarioControllerTest extends ControllerTest {

    private BuscadorDeUsuario buscador = mock(BuscadorDeUsuario.class);
    private BuscarUsuarioController controller = new BuscarUsuarioController(buscador);

    private BuscadorDeUsuario usuarioService = mock(BuscadorDeUsuario.class);
    private ControllerAdvice advice = new ControllerAdvice(usuarioService);

    @Override
    public Object getController() { return controller; }

    @Override
    protected Object getControllerAdvice() {
        return advice;
    }

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        when(usuarioService.getUsuarioLogado()).thenReturn(mock(Usuario.class));
    }

    @Test
    public void listar_retorna_view() throws Exception {
        mockMvc.perform(get("/auth/usuario/"))
               .andExpect(status().isOk())
               .andExpect(view().name("usuario/usuarios"));
    }

    @Test
    public void grid_retorna_json() throws Exception {
        List<UsuarioDTO> registros = new ArrayList<>();
        registros.add(mockDTOs());
        RespostaConsulta<UsuarioDTO> respostaConsulta = new RespostaConsulta<>(registros, 1L);

        when(buscador.buscar(any())).thenReturn(respostaConsulta);

        mockMvc.perform(get("/api/auth/usuarios").param("dataTablesRequest", "{}"))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string(
                        "{\"draw\":0,\"recordsTotal\":1,\"recordsFiltered\":1,\"data\":[{\"id\":1,\"nome\":\"User Name\",\"cpf\":\"000.000.000-00\",\"siape\":\"000001\",\"login\":\"CGU\\\\user\",\"email\":\"user@cgu.gov.br\",\"ativo\":true}],\"error\":null}"));
    }

    @Test
    public void verificarAtivo__deve_retornar_OK_true() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setAtivo(true);
        when(buscador.buscarPorLogin(any())).thenReturn(usuario);

        mockMvc.perform(get("/api/auth/usuario/00000000000/ativo"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

    @Test
    public void verificarAtivo__deve_retornar_OK_false() throws Exception {
        Usuario usuario = null;
        when(buscador.buscarPorLogin(any())).thenReturn(usuario);

        mockMvc.perform(get("/api/auth/usuario/00000000000/ativo"))
                .andExpect(status().isOk())
                .andExpect(content().string("false"));
    }

    @Test
    public void verificarAtivo__deve_retornar_bad_request() throws Exception {
        when(buscador.buscarPorLogin(any())).thenThrow(EntidadeInvalidaException.class);

        mockMvc.perform(get("/api/auth/usuario/00000000000/ativo"))
                .andExpect(status().isBadRequest());
    }

    private UsuarioDTO mockDTOs() {
        return new UsuarioDTO(1, "User Name", "00000000000", "000001", "CGU\\user", "user@cgu.gov.br", true);
    }
}
    