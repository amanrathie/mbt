package integracao.admin.usuario;

import integracao.BootIntegracaoTest;
import integracao.AdminPage;
import org.junit.Test;

import static br.gov.cgu.projetoexemplosb.Constantes.OPERACAO_REALIZADA_COM_SUCESSO;
import static integracao.AdminPage.esperarLoadingsSumirem;
import static integracao.admin.usuario.BuscarUsuarioPage.ir;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class BuscarUsuarioIT extends BootIntegracaoTest {

    private BuscarUsuarioPage page;

    @Test
    public void estado_inicial_da_tela() {
        AdminPage.fazerLoginComTodasPermissoes();
        page = ir();

        esperarLoadingsSumirem();

        assertThat(page.getConteudoTabela(), is(
                "000.000.000-01 Renan CGU\\renanlf   " +
                        "000.000.000-09 Fulano Desativado CGU\\fulanodesativado   " +
                        "100.000.000-01 Teste ADMIN 1 CGU\\USER_CGSIS_TESTE_01   " +
                        "100.000.000-02 Teste CGSIS 2 CGU\\USER_CGSIS_TESTE_02   " +
                        "100.000.000-03 Teste CGSIS 3 CGU\\USER_CGSIS_TESTE_03   " +
                        "100.000.000-04 Teste CGSIS 4 CGU\\USER_CGSIS_TESTE_04   " +
                        "100.000.000-05 Teste CGSIS 5 CGU\\USER_CGSIS_TESTE_05   " +
                        "100.000.000-06 Teste CGSIS 6 CGU\\USER_CGSIS_TESTE_06   " +
                        "100.000.000-07 Teste CGSIS 7 CGU\\USER_CGSIS_TESTE_07   " +
                        "100.000.000-08 Teste CGSIS 8 CGU\\USER_CGSIS_TESTE_08   " +
                        "100.000.000-09 Teste CGSIS 9 CGU\\USER_CGSIS_TESTE_09   " +
                        "100.000.000-10 Teste CGSIS 10 (expirado) CGU\\USER_CGSIS_TESTE_10   " +
                        "100.000.000-13 Teste CGTEC 1 CGU\\USER_CGTEC_TESTE_01   " +
                        "100.000.000-14 SISTEMA ACESSO CGU\\sistema_acesso   " +
                        "100.000.000-15 Pimenta CGU\\gabrielcp  "));
    }

    @Test
    public void exibir_apenas_usuarios_ativos() {
        AdminPage.fazerLoginComTodasPermissoes();
        page = ir();
        page.expandirFiltros();
        esperarLoadingsSumirem();

        page.definirSituacaoUsuariosResultado("Ativos");

        page.clicarEmFiltrar();

        assertThat(page.getConteudoTabela(), is(
                "000.000.000-01 Renan CGU\\renanlf   " +
                        "100.000.000-01 Teste ADMIN 1 CGU\\USER_CGSIS_TESTE_01   " +
                        "100.000.000-02 Teste CGSIS 2 CGU\\USER_CGSIS_TESTE_02   " +
                        "100.000.000-03 Teste CGSIS 3 CGU\\USER_CGSIS_TESTE_03   " +
                        "100.000.000-04 Teste CGSIS 4 CGU\\USER_CGSIS_TESTE_04   " +
                        "100.000.000-05 Teste CGSIS 5 CGU\\USER_CGSIS_TESTE_05   " +
                        "100.000.000-06 Teste CGSIS 6 CGU\\USER_CGSIS_TESTE_06   " +
                        "100.000.000-07 Teste CGSIS 7 CGU\\USER_CGSIS_TESTE_07   " +
                        "100.000.000-08 Teste CGSIS 8 CGU\\USER_CGSIS_TESTE_08   " +
                        "100.000.000-09 Teste CGSIS 9 CGU\\USER_CGSIS_TESTE_09   " +
                        "100.000.000-10 Teste CGSIS 10 (expirado) CGU\\USER_CGSIS_TESTE_10   " +
                        "100.000.000-13 Teste CGTEC 1 CGU\\USER_CGTEC_TESTE_01   " +
                        "100.000.000-14 SISTEMA ACESSO CGU\\sistema_acesso   " +
                        "100.000.000-15 Pimenta CGU\\gabrielcp  "));
    }

    @Test
    public void exibir_apenas_usuarios_excluidos() {
        AdminPage.fazerLoginComTodasPermissoes();
        page = ir();
        page.expandirFiltros();
        esperarLoadingsSumirem();

        page.definirSituacaoUsuariosResultado("Inativos");

        page.clicarEmFiltrar();

        assertThat(page.getConteudoTabela(), is(
                "000.000.000-09 Fulano Desativado CGU\\fulanodesativado  "));
    }

    @Test
    public void exibir_usuarios_ativos_e_excluidos() {
        AdminPage.fazerLoginComTodasPermissoes();
        page = ir();
        page.expandirFiltros();
        esperarLoadingsSumirem();

        page.definirSituacaoUsuariosResultado("Todos");

        page.clicarEmFiltrar();

        assertThat(page.getConteudoTabela(), is("000.000.000-01 Renan CGU\\renanlf   " +
                "000.000.000-09 Fulano Desativado CGU\\fulanodesativado   " +
                "100.000.000-01 Teste ADMIN 1 CGU\\USER_CGSIS_TESTE_01   100.000.000-02 Teste CGSIS 2 CGU\\USER_CGSIS_TESTE_02   " +
                "100.000.000-03 Teste CGSIS 3 CGU\\USER_CGSIS_TESTE_03   100.000.000-04 Teste CGSIS 4 CGU\\USER_CGSIS_TESTE_04   " +
                "100.000.000-05 Teste CGSIS 5 CGU\\USER_CGSIS_TESTE_05   100.000.000-06 Teste CGSIS 6 CGU\\USER_CGSIS_TESTE_06   " +
                "100.000.000-07 Teste CGSIS 7 CGU\\USER_CGSIS_TESTE_07   100.000.000-08 Teste CGSIS 8 CGU\\USER_CGSIS_TESTE_08   " +
                "100.000.000-09 Teste CGSIS 9 CGU\\USER_CGSIS_TESTE_09   100.000.000-10 Teste CGSIS 10 (expirado) CGU\\USER_CGSIS_TESTE_10   " +
                "100.000.000-13 Teste CGTEC 1 CGU\\USER_CGTEC_TESTE_01   100.000.000-14 SISTEMA ACESSO CGU\\sistema_acesso   " +
                "100.000.000-15 Pimenta CGU\\gabrielcp  "));
    }

    @Test
    public void desativar_usuario() throws Exception {
        AdminPage.fazerLoginComTodasPermissoes();
        page = ir();
        esperarLoadingsSumirem();

        page.clicarNoBotaoAlternarAtivacao(1);

        assertThat(page.getMensagemDeSucesso(), is(OPERACAO_REALIZADA_COM_SUCESSO));
    }

}
