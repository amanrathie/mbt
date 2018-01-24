package integracao.admin.unidade;

import br.gov.cgu.projetoexemplosb.negocio.unidade.TipoUnidade;
import integracao.BootIntegracaoTest;
import integracao.AdminPage;
import integracao.TestadorDeAutocomplete;
import org.junit.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static br.gov.cgu.projetoexemplosb.Constantes.OPERACAO_REALIZADA_COM_SUCESSO;
import static integracao.AdminPage.MENSAGEM_ERRO_CAMPO_OBRIGATORIO;
import static integracao.admin.unidade.CadastrarUnidadePage.ir;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class CadastrarUnidadeIT extends BootIntegracaoTest {

    private CadastrarUnidadePage page;

    @Test
    public void estado_inicial_da_tela() throws Exception {
        AdminPage.fazerLoginComTodasPermissoes();
        page = ir();

        assertThat(page.getValoresDaComboTipos(), is("-- Selecione -- " +
                Arrays.stream(TipoUnidade.values()).map(TipoUnidade::toString).collect(Collectors.joining(" "))));

        assertThat(page.getCampoNome(), is(""));
    }

    @Test
    public void cadastrar_unidade_novo_com_sucesso() throws Exception {
        AdminPage.fazerLoginComTodasPermissoes();
        page = ir();

        String tipo = "Unidade de Auditoria Interna Governamental";
        String nome = "Nome";
        String codigo = "Codigo";
        String sigla = "UFA";
        String email = "dada@cgu.gov.br";
        String telefone = "61999991111";
        String municipio = "Belo Hor";

        page.preencherTipo(tipo);
        page.preencherNome(nome);
        page.preencherCodigo(codigo);
        page.preencherSigla(sigla);
        TestadorDeAutocomplete.buscarESelecionar("idUnidadeSuperior", "DTI", "200003");
        TestadorDeAutocomplete.buscarESelecionar("idOrgao", "CGU", "CGU");
        page.preencherEmail(email);
        page.preencherTelefone(telefone);
        page.preencherMunicipio(municipio);
        TestadorDeAutocomplete.buscarESelecionar("gestores", "Teste ADMIN 1",   "Teste ADMIN 1");
        TestadorDeAutocomplete.buscarESelecionar("gestores", "Teste CGSIS 2",   "Teste CGSIS 2");

        page.clicarEmSalvar();

        assertThat(page.getMensagemDeSucesso(), is(OPERACAO_REALIZADA_COM_SUCESSO));

        assertThat(page.getCampoTipo(), is(tipo));
        assertThat(page.getCampoNome(), is(nome));
        assertThat(page.getCampoCodigo(), is(codigo));
        assertThat(page.getCampoSigla(), is(sigla));
        assertThat(page.getCampoUnidadeSuperior(), is("3"));
        assertThat(page.getCampoOrgao(), is("2"));
        assertThat(page.getCampoEmail(), is(email));
        assertThat(page.getCampoTelefone(), is("(61) 99999-1111"));
        assertThat(page.getCampoMunicipio(), is("2"));
        assertThat(page.getCampoGestores(), is("11,12"));
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
