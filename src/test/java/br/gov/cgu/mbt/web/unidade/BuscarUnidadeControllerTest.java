package br.gov.cgu.mbt.web.unidade;

import br.gov.cgu.mbt.aplicacao.auth.BuscadorDeUsuario;
import br.gov.cgu.mbt.aplicacao.unidade.BuscadorDeUnidade;
import br.gov.cgu.mbt.aplicacao.unidade.UnidadeDTO;
import br.gov.cgu.mbt.negocio.auth.Usuario;
import br.gov.cgu.mbt.negocio.nucleo.UF;
import br.gov.cgu.mbt.negocio.unidade.TipoUnidade;
import br.gov.cgu.mbt.web.ControllerAdvice;
import br.gov.cgu.mbt.web.unidade.BuscarUnidadeController;
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

public class BuscarUnidadeControllerTest extends ControllerTest {

    private BuscadorDeUnidade buscador = mock(BuscadorDeUnidade.class);
    private BuscarUnidadeController controller = new BuscarUnidadeController(buscador);

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
        mockMvc.perform(get("/auth/unidade/").param("nome", "Diret"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("filtro"))
                .andExpect(model().attribute("tipos", TipoUnidade.values()))
                .andExpect(model().attribute("ufs", UF.values()))
                .andExpect(view().name("unidade/unidades"));
    }

    @Test
    public void popup_retorna_view_com_modoPopup() throws Exception {
        mockMvc.perform(get("/popup/unidade/").param("nome", "Diret"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("filtro"))
                .andExpect(model().attribute("tipos", TipoUnidade.values()))
                .andExpect(model().attribute("ufs", UF.values()))
                .andExpect(model().attributeExists("modoPopup"))
                .andExpect(view().name("unidade/unidades"));
    }

    @Test
    public void grid_retorna_json() throws Exception {
        List<UnidadeDTO> registros = new ArrayList<>();
        registros.add(mockDTOs());
        RespostaConsulta<UnidadeDTO> respostaConsulta = new RespostaConsulta<>(registros, 1L);

        when(buscador.buscar(any())).thenReturn(respostaConsulta);

        mockMvc.perform(get("/api/auth/unidade"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string(
                        "{\"draw\":0,\"recordsTotal\":1,\"recordsFiltered\":1,\"data\":[{\"id\":1,\"tipo\":\"UAUD\",\"nome\":\"Ministerio\"," +
                                "\"sigla\":\"MM\",\"codigo\":\"12345\",\"uf\":\"DF\",\"municipio\":\"Brasília\",\"ativo\":true}],\"error\":null}"));
    }

    private UnidadeDTO mockDTOs() {
        return new UnidadeDTO(1, 1, "Ministerio", "MM", "12345", "DF", "Brasília", true);
    }
}