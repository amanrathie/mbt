package integracao.admin.unidade;

import integracao.BootIntegracaoTest;
import integracao.AdminPage;
import integracao.TestadorDeDataTables;
import org.junit.Test;

import br.gov.cgu.mbt.negocio.unidade.TipoUnidade;

import java.util.Arrays;
import java.util.stream.Collectors;

import static br.gov.cgu.mbt.Constantes.OPERACAO_REALIZADA_COM_SUCESSO;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


public class BuscarUnidadeIT extends BootIntegracaoTest {

    private BuscarUnidadePage page;

    @Test
    public void estado_inicial_da_tela() throws Exception {
        BuscarUnidadePage.fazerLoginComTodasPermissoes();
        page = BuscarUnidadePage.ir();
        page.expandirFiltros();
        AdminPage.esperarLoadingsSumirem();

        assertThat(page.getConteudoTabela(), is(
                "UAIG Diretoria de TI DTI 200003 GO Pirenópolis   " +
                        "UAIG Diretoria de TI old DSI 200003 GO Goiânia   " +
                        "UAIG DTI/CGTEC - Coordenação-Geral de Infra-Estrutura Tecnológica DTI/CGTEC 200004 RJ Rio de Janeiro   " +
                        "UAIG DTI/CGSIS - Coordenação-Geral de Sistemas de Informação DTI/CGSIS 200005 SP São Paulo   " +
                        "UAIG DTI/CGSIS/Serviço de Controle Interno DTI/CGSIS/COSIS 200006 BA Salvador   " +
                        "UAUD Ministerio X MX 200001 DF Brasília   " +
                        "UPAG UPAG Y UPAGY 200002 MG Belo Horizonte"));

        assertThat(page.getTipos(), is("Todos " +
                Arrays.stream(TipoUnidade.values()).map(TipoUnidade::toString).collect(Collectors.joining(" "))));
    }

    @Test
    public void testar_aplicacao_de_filtros() throws Exception {
        BuscarUnidadePage.fazerLoginComTodasPermissoes();
        page = BuscarUnidadePage.ir();
        AdminPage.esperarLoadingsSumirem();
        page.expandirFiltros();

        page.preencherFiltroNome("Ministerio");
        page.clicarEmFiltrar();
        AdminPage.esperarLoadingsSumirem();
        TestadorDeDataTables.assertColunaFiltradaPorTexto("lista", "Nome", "Ministerio");

        page.limparFiltros();
        page.preencherFiltroSituacao("Inativos");
        page.clicarEmFiltrar();
        AdminPage.esperarLoadingsSumirem();
        assertThat(page.getConteudoTabela(), is("UAIG Diretoria de TI old DSI 200003 GO Goiânia"));

        page.limparFiltros();
        page.preencherFiltroTipo("Unidade Pagadora");
        page.clicarEmFiltrar();
        AdminPage.esperarLoadingsSumirem();
        TestadorDeDataTables.assertColunaFiltradaPorTexto("lista", "Tipo", "UPAG");

        page.limparFiltros();
        page.preencherFiltroCodigo("200003");
        page.clicarEmFiltrar();
        AdminPage.esperarLoadingsSumirem();
        TestadorDeDataTables.assertColunaFiltradaPorTexto("lista", "Código", "200003");

        page.limparFiltros();
        page.preencherFiltroSigla("DTI");
        page.clicarEmFiltrar();
        AdminPage.esperarLoadingsSumirem();
        TestadorDeDataTables.assertColunaFiltradaPorTexto("lista", "Sigla", "DTI");

        page.limparFiltros();
        page.preencherFiltroUf("Distrito Federal");
        page.clicarEmFiltrar();
        AdminPage.esperarLoadingsSumirem();
        TestadorDeDataTables.assertColunaFiltradaPorTexto("lista", "UF", "DF");

        page.limparFiltros();
        page.preencherFiltroMunicipio("Pirenópolis");
        page.clicarEmFiltrar();
        AdminPage.esperarLoadingsSumirem();
        TestadorDeDataTables.assertColunaFiltradaPorTexto("lista", "Município", "Pirenópolis");
    }

    @Test
    public void desativar_unidade() throws Exception {
        BuscarUnidadePage.fazerLoginComTodasPermissoes();
        page = BuscarUnidadePage.ir();
        AdminPage.esperarLoadingsSumirem();

        page.clicarNoBotaoAlternarAtivacao(1);

        assertThat(page.getMensagemDeSucesso(), is(OPERACAO_REALIZADA_COM_SUCESSO));
    }
}
