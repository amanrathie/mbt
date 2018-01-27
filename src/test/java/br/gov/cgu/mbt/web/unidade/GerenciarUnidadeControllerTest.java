package br.gov.cgu.mbt.web.unidade;

import br.gov.cgu.mbt.aplicacao.auth.BuscadorDeUsuario;
import br.gov.cgu.mbt.aplicacao.unidade.BuscadorDeUnidade;
import br.gov.cgu.mbt.aplicacao.unidade.GerenciadorDeUnidade;
import br.gov.cgu.mbt.aplicacao.unidade.UnidadeNaoPodeSerAtivadaException;
import br.gov.cgu.mbt.negocio.auth.Usuario;
import br.gov.cgu.mbt.negocio.unidade.TipoUnidade;
import br.gov.cgu.mbt.negocio.unidade.Unidade;
import br.gov.cgu.mbt.web.ControllerAdvice;
import br.gov.cgu.mbt.web.unidade.GerenciarUnidadeController;
import br.gov.cgu.persistencia.jpa.EntidadeInvalidaException;
import br.gov.cgu.test.mvc.ControllerTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;

import static br.gov.cgu.mbt.Constantes.OPERACAO_REALIZADA_COM_SUCESSO;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class GerenciarUnidadeControllerTest extends ControllerTest {

    private GerenciadorDeUnidade gerenciadorDeUnidade = mock(GerenciadorDeUnidade.class);
    private BuscadorDeUnidade buscadorDeUnidade = mock(BuscadorDeUnidade.class);
    private GerenciarUnidadeController controller = new GerenciarUnidadeController(gerenciadorDeUnidade, buscadorDeUnidade);

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
    public void alterarAtivacao__deve_chamar_servico_via_url_generica_e_retornar_mensagem_de_sucesso() throws Exception {
        mockMvc.perform(post("/auth/unidade/alternar-ativacao/3"))
               .andExpect(status().isOk())
               .andExpect(content().string(OPERACAO_REALIZADA_COM_SUCESSO));
    }

    @Test
    public void alterarAtivacao__deve_chamar_servico_e_retornar_mensagem_de_sucesso() throws Exception {
        mockMvc.perform(post("/auth/unidade/3/ativar"))
                .andExpect(status().isOk())
                .andExpect(content().string(OPERACAO_REALIZADA_COM_SUCESSO));
    }

    @Test
    public void alterarAtivacao__deve_chamar_servico_e_retornar_mensagem_de_erro() throws Exception {
        doAnswer(invocationOnMock -> {
            Unidade unidade = new Unidade();
            unidade.setUnidadeSuperior(new Unidade());
            throw new UnidadeNaoPodeSerAtivadaException(unidade);
        }).when(gerenciadorDeUnidade).alternarAtivacao(any());

        mockMvc.perform(post("/auth/unidade/3/ativar"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("A unidade null não pode ser ativada com sua unidade superior(null) inativa."));
    }

    @Test
    public void exibirTelaCadastro__preenche_model_e_retorna_view() throws Exception {
        mockMvc.perform(get("/auth/unidade/novo"))
                .andExpect(status().isOk())
                .andExpect(view().name("unidade/unidade"))
                .andExpect(model().attributeExists("dto"))
                .andExpect(model().attribute("tipos", is(TipoUnidade.values())));
    }

    @Test
    public void exibirTelaEdicao__preenche_model_e_retorna_view() throws Exception {
        Unidade unidade = new Unidade();
        when(buscadorDeUnidade.getParaEdicao(1)).thenReturn(unidade);

        mockMvc.perform(get("/auth/unidade/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("unidade/unidade"))
                .andExpect(model().attribute("dto", unidade))
                .andExpect(model().attribute("tipos", TipoUnidade.values()));
    }

    @Test
    public void exibirTelaEdicao__deve_ter_mensagem_de_sucesso_se_parametro_estiver_presente() throws Exception {
        Unidade unidade = new Unidade();
        when(buscadorDeUnidade.getParaEdicao(1)).thenReturn(unidade);

        mockMvc.perform(get("/auth/unidade/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("unidade/unidade"))
                .andExpect(model().attribute("dto", unidade))
                .andExpect(model().attribute("tipos", TipoUnidade.values()));
    }

    @Test
    public void post__salva_com_sucesso_e_exibe_mensagem() throws Exception {
        // given
        Unidade unidade = new Unidade();
        unidade.setId(1);

        String jsonUnidade = "{\"id\": 1}";

        // when
        when(gerenciadorDeUnidade.salvar(any())).thenReturn(unidade);

        // then
        mockMvc.perform(post("/api/auth/unidade/salvar")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonUnidade))
                .andExpect(status().isOk());
    }

    @Test
    public void post__deve_informar_que_a_entidade_eh_invalida() throws Exception {
        String jsonUnidade = "{\"id\": 10}";

        EntidadeInvalidaException e = new EntidadeInvalidaException("Nome é campo obrigatório.");
        // when
        when(gerenciadorDeUnidade.salvar(any())).thenThrow(e);

        // then
        mockMvc.perform(post("/api/auth/unidade/salvar")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonUnidade))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Nome é campo obrigatório."));
    }
}