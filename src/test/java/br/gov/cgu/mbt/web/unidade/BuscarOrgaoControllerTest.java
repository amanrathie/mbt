package br.gov.cgu.mbt.web.unidade;

import br.gov.cgu.mbt.aplicacao.auth.BuscadorDeUsuario;
import br.gov.cgu.mbt.aplicacao.unidade.BuscadorDeOrgao;
import br.gov.cgu.mbt.aplicacao.unidade.OrgaoDTO;
import br.gov.cgu.mbt.negocio.auth.Usuario;
import br.gov.cgu.mbt.web.ControllerAdvice;
import br.gov.cgu.mbt.web.unidade.BuscarOrgaoController;
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

public class BuscarOrgaoControllerTest extends ControllerTest {

    private BuscadorDeOrgao buscador = mock(BuscadorDeOrgao.class);
    private BuscarOrgaoController controller = new BuscarOrgaoController(buscador);

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
        mockMvc.perform(get("/auth/orgao/").param("nome", "Diret"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("filtro"))
                .andExpect(view().name("orgao/orgaos"));
    }

    @Test
    public void popup_retorna_view_com_modoPopup() throws Exception {
        mockMvc.perform(get("/popup/orgao/").param("nome", "Diret"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("filtro"))
                .andExpect(model().attributeExists("modoPopup"))
                .andExpect(view().name("orgao/orgaos"));
    }

    @Test
    public void grid_retorna_json() throws Exception {
        List<OrgaoDTO> registros = new ArrayList<>();
        registros.add(mockDTOs());
        RespostaConsulta<OrgaoDTO> respostaConsulta = new RespostaConsulta<>(registros, 1L);

        when(buscador.buscar(any())).thenReturn(respostaConsulta);

        mockMvc.perform(get("/api/auth/orgao"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string(
                        "{\"draw\":0,\"recordsTotal\":1,\"recordsFiltered\":1,\"data\":[" +
                                "{\"id\":1,\"tipo\":\"Administração Direta Estadual\",\"nome\":\"Ministerio\",\"sigla\":\"MM\",\"codigo\":\"12345\",\"ativo\":true}]" +
                                ",\"error\":null}"));
    }

    private OrgaoDTO mockDTOs() {
        return new OrgaoDTO(1, 1, "Ministerio", "MM", "12345", true);
    }
}