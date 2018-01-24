package integracao.admin.orgao;

import br.gov.cgu.projetoexemplosb.negocio.unidade.TipoOrgao;
import integracao.BootIntegracaoTest;
import integracao.AdminPage;
import org.junit.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static br.gov.cgu.projetoexemplosb.Constantes.OPERACAO_REALIZADA_COM_SUCESSO;
import static integracao.AdminPage.MENSAGEM_ERRO_CAMPO_OBRIGATORIO;
import static integracao.admin.orgao.CadastrarOrgaoPage.ir;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class CadastrarOrgaoIT extends BootIntegracaoTest {

    private CadastrarOrgaoPage page;

    @Test
    public void estado_inicial_da_tela() throws Exception {
        AdminPage.fazerLoginComTodasPermissoes();
        page = ir();

        assertThat(page.getValoresDaComboTipos(), is("-- Selecione -- " +
                Arrays.stream(TipoOrgao.values()).map(TipoOrgao::toString).collect(Collectors.joining(" "))));

        assertThat(page.getCampoNome(), is(""));
        assertThat(page.getCampoCodigo(), is(""));
        assertThat(page.getCampoSigla(), is(""));
    }

    @Test
    public void cadastrar_unidade_novo_com_sucesso() throws Exception {
        AdminPage.fazerLoginComTodasPermissoes();
        page = ir();

        String tipo = "Administração Direta";
        String nome = "Nome";
        String codigo = "Codigo";
        String sigla = "UFA";

        page.preencherTipo(tipo);
        page.preencherNome(nome);
        page.preencherCodigo(codigo);
        page.preencherSigla(sigla);

        page.clicarEmSalvar();

        assertThat(page.getMensagemDeSucesso(), is(OPERACAO_REALIZADA_COM_SUCESSO));
        assertThat(page.getCampoTipo(), is(tipo));
        assertThat(page.getCampoNome(), is(nome));
        assertThat(page.getCampoCodigo(), is(codigo));
        assertThat(page.getCampoSigla(), is(sigla));
    }

    @Test
    public void verifica_validacoes() throws Exception {
        AdminPage.fazerLoginComTodasPermissoes();
        page = ir();

        page.clicarEmSalvar();

        assertThat(page.getMensagemDeErroDoCampo("tipo"), is(MENSAGEM_ERRO_CAMPO_OBRIGATORIO));
        assertThat(page.getMensagemDeErroDoCampo("nome"), is(MENSAGEM_ERRO_CAMPO_OBRIGATORIO));
        assertThat(page.getMensagemDeErroDoCampo("codigo"), is(MENSAGEM_ERRO_CAMPO_OBRIGATORIO));
        assertThat(page.getMensagemDeErroDoCampo("sigla"), is(MENSAGEM_ERRO_CAMPO_OBRIGATORIO));
    }
}
