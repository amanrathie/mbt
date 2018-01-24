package integracao.admin.orgao;

import br.gov.cgu.projetoexemplosb.negocio.unidade.TipoOrgao;
import integracao.BootIntegracaoTest;
import org.junit.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static br.gov.cgu.projetoexemplosb.Constantes.OPERACAO_REALIZADA_COM_SUCESSO;
import static integracao.AdminPage.esperarLoadingsSumirem;
import static integracao.TestadorDeDataTables.assertColunaFiltradaPorTexto;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


public class BuscarOrgaoIT extends BootIntegracaoTest {

    private BuscarOrgaoPage page;

    @Test
    public void estado_inicial_da_tela() throws Exception {
        BuscarOrgaoPage.fazerLoginComTodasPermissoes();
        page = BuscarOrgaoPage.ir();
        page.expandirFiltros();
        esperarLoadingsSumirem();

        assertThat(page.getConteudoTabela(), is(
                "Administração Direta Estadual Controladoria-Geral da União CGU 100001   " +
                        "Administração Direta Câmara dos Deputados CD 500003   " +
                        "Administração Direta Estadual Presidência da República PR 100000   " +
                        "Administração Indireta Estadual Qualquer orgao inativo " +
                        "QOI 500001   Administração Direta Senado Federal SF 500002   " +
                        "Administração Direta Supremo Tribunal Federal STF 500004   " +
                        "Administração Direta Tribunal Superior Eleitoral TSE 500005"));

        assertThat(page.getTipos(), is("Todos " +
                Arrays.stream(TipoOrgao.values()).map(TipoOrgao::toString).collect(Collectors.joining(" "))));
    }

    @Test
    public void testar_aplicacao_de_filtros() throws Exception {
        BuscarOrgaoPage.fazerLoginComTodasPermissoes();
        page = BuscarOrgaoPage.ir();
        esperarLoadingsSumirem();
        page.expandirFiltros();

        page.preencherFiltroNome("Ministerio");
        page.clicarEmFiltrar();
        esperarLoadingsSumirem();
        assertColunaFiltradaPorTexto("lista", "Nome", "Ministerio");

        page.limparFiltros();
        page.preencherFiltroSituacao("Inativos");
        page.clicarEmFiltrar();
        esperarLoadingsSumirem();
        assertThat(page.getConteudoTabela(), is("Administração Indireta Estadual Qualquer orgao inativo QOI 500001"));

        page.limparFiltros();
        page.preencherFiltroTipo("Administração Direta Estadual");
        page.clicarEmFiltrar();
        esperarLoadingsSumirem();
        assertColunaFiltradaPorTexto("lista", "Tipo", "Administração Direta Estadual");

        page.limparFiltros();
        page.preencherFiltroCodigo("200003");
        page.clicarEmFiltrar();
        esperarLoadingsSumirem();
        assertColunaFiltradaPorTexto("lista", "Código", "200003");

        page.limparFiltros();
        page.preencherFiltroSigla("DTI");
        page.clicarEmFiltrar();
        esperarLoadingsSumirem();
        assertColunaFiltradaPorTexto("lista", "Sigla", "DTI");
    }

    @Test
    public void desativar_orgao() throws Exception {
        BuscarOrgaoPage.fazerLoginComTodasPermissoes();
        page = BuscarOrgaoPage.ir();
        esperarLoadingsSumirem();

        page.clicarNoBotaoAlternarAtivacao(1);

        assertThat(page.getMensagemDeSucesso(), is(OPERACAO_REALIZADA_COM_SUCESSO));
    }
}
