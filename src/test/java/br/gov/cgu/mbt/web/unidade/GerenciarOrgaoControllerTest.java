package br.gov.cgu.mbt.web.unidade;

import br.gov.cgu.mbt.aplicacao.auth.BuscadorDeUsuario;
import br.gov.cgu.mbt.aplicacao.unidade.BuscadorDeOrgao;
import br.gov.cgu.mbt.aplicacao.unidade.GerenciadorDeOrgao;
import br.gov.cgu.mbt.negocio.auth.Usuario;
import br.gov.cgu.mbt.negocio.unidade.Orgao;
import br.gov.cgu.mbt.negocio.unidade.TipoOrgao;
import br.gov.cgu.mbt.web.ControllerAdvice;
import br.gov.cgu.mbt.web.unidade.GerenciarOrgaoController;
import br.gov.cgu.persistencia.jpa.EntidadeInvalidaException;
import br.gov.cgu.test.mvc.ControllerTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;

import static br.gov.cgu.mbt.Constantes.OPERACAO_REALIZADA_COM_SUCESSO;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class GerenciarOrgaoControllerTest extends ControllerTest {

    private GerenciadorDeOrgao gerenciadorDeOrgao = mock(GerenciadorDeOrgao.class);
    private BuscadorDeOrgao buscadorDeOrgao = mock(BuscadorDeOrgao.class);
    private GerenciarOrgaoController controller = new GerenciarOrgaoController(gerenciadorDeOrgao, buscadorDeOrgao);

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
    public void alterarAtivacao__deve_chamar_servico_de_ativacao_e_retornar_mensagem_de_sucesso() throws Exception {
        mockMvc.perform(post("/auth/orgao/3/ativar"))
                .andExpect(status().isOk())
                .andExpect(content().string(OPERACAO_REALIZADA_COM_SUCESSO));
    }

    @Test
    public void alterarAtivacao__deve_chamar_servico_de_inativacao_e_retornar_mensagem_de_sucesso() throws Exception {
        mockMvc.perform(post("/auth/orgao/3/inativar"))
               .andExpect(status().isOk())
               .andExpect(content().string(OPERACAO_REALIZADA_COM_SUCESSO));
    }

    @Test
    public void alterarAtivacao__deve_chamar_servico_de_alternar_ativacao_e_retornar_mensagem_de_sucesso() throws Exception {
        mockMvc.perform(post("/auth/orgao/alternar-ativacao/3"))
               .andExpect(status().isOk())
               .andExpect(content().string(OPERACAO_REALIZADA_COM_SUCESSO));
    }

    @Test
    public void exibirTelaCadastro__preenche_model_e_retorna_view() throws Exception {
        mockMvc.perform(get("/auth/orgao/novo"))
                .andExpect(status().isOk())
                .andExpect(view().name("orgao/orgao"))
                .andExpect(model().attributeExists("dto"))
                .andExpect(model().attribute("tipos", is(TipoOrgao.values())));
    }

    @Test
    public void exibirTelaEdicao__preenche_model_e_retorna_view() throws Exception {
        Orgao orgao = new Orgao();
        when(buscadorDeOrgao.getParaEdicao(1)).thenReturn(orgao);

        mockMvc.perform(get("/auth/orgao/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("orgao/orgao"))
                .andExpect(model().attribute("dto", orgao))
                .andExpect(model().attribute("tipos", TipoOrgao.values()));
    }

    @Test
    public void exibirTelaEdicao__deve_ter_mensagem_de_sucesso_se_parametro_estiver_presente() throws Exception {
        Orgao orgao = new Orgao();
        when(buscadorDeOrgao.getParaEdicao(1)).thenReturn(orgao);

        mockMvc.perform(get("/auth/orgao/1?m=1"))
                .andExpect(status().isOk())
                .andExpect(view().name("orgao/orgao"))
                .andExpect(model().attribute("dto", orgao))
                .andExpect(model().attribute("tipos", TipoOrgao.values()));
    }

    @Test
    public void post__salva_com_sucesso_e_exibe_mensagem() throws Exception {
        // given
        Orgao orgao = new Orgao();
        orgao.setId(1);

        String jsonOrgao = "{\"id\": 1}";

        // when
        when(gerenciadorDeOrgao.salvar(any())).thenReturn(orgao);

        // then
        mockMvc.perform(post("/api/auth/orgao/salvar")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonOrgao))
                .andExpect(status().isOk());
    }

    @Test
    public void post__deve_informar_que_o_orgao_eh_invalido() throws Exception {
        String jsonOrgao = "{\"id\": 10}";

        EntidadeInvalidaException e = new EntidadeInvalidaException("Nome é campo obrigatório.");
        // when
        when(gerenciadorDeOrgao.salvar(any())).thenThrow(e);

        // then
        mockMvc.perform(post("/api/auth/orgao/salvar")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonOrgao))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Nome é campo obrigatório."));
    }

}