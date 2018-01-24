package integracao.admin.usuario;

import br.gov.cgu.projetoexemplosb.web.auth.GerenciarUsuarioController;
import integracao.BootIntegracaoTest;
import integracao.AdminPage;
import org.junit.Test;

import static br.gov.cgu.projetoexemplosb.web.auth.GerenciarUsuarioController.OPERACAO_COM_SUCESSO;
import static integracao.AdminPage.MENSAGEM_ERRO_CAMPO_OBRIGATORIO;
import static integracao.admin.usuario.CadastrarUsuarioPage.ir;
import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;

public class CadastrarUsuarioIT extends BootIntegracaoTest {

    private CadastrarUsuarioPage page;

    @Test
    public void estado_inicial_da_tela() {
        AdminPage.fazerLoginComTodasPermissoes();
        page = ir();

        assertThat(page.getNome(), is(""));
        assertThat(page.getCpf(), is(""));
        assertThat(page.getLogin(), is(""));
        assertThat(page.getSiape(), is(""));
        assertThat(page.getUnidades(), is(""));
        assertThat(page.getEmail(), is(""));
        assertThat(page.getTelefone(), is(""));
        assertThat(page.getAdmin(), is(false));
    }

    @Test
    public void verifica_validacoes() {
        AdminPage.fazerLoginComTodasPermissoes();
        page = ir();

        page.clicarEmSalvar();

        assertThat(page.getMensagemDeErroDoCampo("nome"), is(MENSAGEM_ERRO_CAMPO_OBRIGATORIO));
        assertThat(page.getMensagemDeErroDoCampo("cpf"), is(MENSAGEM_ERRO_CAMPO_OBRIGATORIO));
        assertThat(page.getMensagemDeErroDoCampo("login"), is(MENSAGEM_ERRO_CAMPO_OBRIGATORIO));
    }

    @Test
    public void btnSalvar_deve_retornar_mensagem_de_sucesso_e_atualizar_url() {
        AdminPage.fazerLoginComTodasPermissoes();
        page = ir();

        String login = "CGU\\login";

        page.setNome("Usuário de Testes");
        page.setCpf("644.180.571-53");
        page.setSiape("0145987");
        page.setLogin(login);
        page.setEmail("mail@cgu.gov.br");
        page.setUnidades("Direto");
        page.setUnidades("Minist");
        page.setTelefone("(61) 9999-8888");

        page.clicarEmSalvar();

        refreshSeDer404("auth/usuario/29");

        assertThat(page.getMensagemDeSucesso(), is(GerenciarUsuarioController.OPERACAO_COM_SUCESSO));
    }

    @Test
    public void btnSalvar_deve_retornar_erro_no_login_e_nao_atualizar_a_url() {
        AdminPage.fazerLoginComTodasPermissoes();
        page = ir();

        String login = "";

        page.setNome("Usuário de Testes");
        page.setCpf("644.180.571-53");
        page.setSiape("0145987");
        page.setLogin(login);
        page.setEmail("mail@cgu.gov.br");
        page.setUnidades("Direto");
        page.setUnidades("Minist");
        page.setTelefone("(61) 9999-8888");

        page.clicarEmSalvar();

        assertThat(page.getMensagemDeErroDoCampo("login"), is(MENSAGEM_ERRO_CAMPO_OBRIGATORIO));
        assertUrlContains("/auth/usuario/novo");
    }

    @Test
    public void usuario_excluido_nao_pode_ser_editado() {
        AdminPage.fazerLoginComTodasPermissoes();
        page = ir(9);

        assertThat(page.isLoginDisabled(), is(true));
    }
    
    private void esperarModalSerExibido() {
        $("#modalUsuarioLogado").waitUntil().is(":visible");
    }
    
    @Test
    public void modalUsuarioLogado_consegue_gerar_novaChaveApi() {
        AdminPage.fazerLoginComTodasPermissoes();
        page = ir();
        
        page.clicarNoLinkUsuarioLogado();
        
        esperarModalSerExibido();
        
        String chaveApiVelha = page.getChaveApiUsuarioLogado();
        
        page.clicarEmBtnChaveApi();
        
        String chaveApiNova = page.esperaEGetChaveApiUsuarioLogado();

        assertThat(chaveApiNova, not(equalTo(chaveApiVelha)));
    }
    
    @Test
    public void modalUsuarioLogado_deve_alterar_dados_usuario_logado() {
        AdminPage.fazerLoginComTodasPermissoes();
        page = ir();
        
        page.clicarNoLinkUsuarioLogado();
        
        esperarModalSerExibido();
        
        page.setEmailUsuarioLogado("mail@cgu.gov.br");
        page.setTelefoneUsuarioLogado("(61) 9999-8888");
        page.clicarEmBtnChaveApi();

        page.clicarEmBtnSalvarUsuarioLogado();

        assertThat(page.getMensagemDeSucesso(), is(OPERACAO_COM_SUCESSO));
    }
}
