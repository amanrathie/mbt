package integracao.admin.unidade;

import integracao.AdminPage;
import integracao.TestadorDeAutocomplete;

import static io.github.seleniumquery.SeleniumQuery.$;

public class BuscarUnidadePage extends AdminPage {

    public static BuscarUnidadePage ir() {
        fazerLoginComTodasPermissoes();
        return ir("auth/unidade", BuscarUnidadePage.class); }

    public String getConteudoTabela() {
        return $("#lista tbody").text().replace("\n"," ").trim();
    }

    public String getTipos(){
        return $("#tipo option").text();
    }

    public void preencherFiltroNome(String nome) {
        $("#nome").val(nome);
    }

    public void clicarEmFiltrar() {
        $("#btnFiltrar").click();
    }

    public void preencherFiltroSituacao(String situacao) {
        $("#ativo").as().select().selectByVisibleText(situacao);
    }

    public void preencherFiltroTipo(String tipo) {
        $("#tipo").as().select().selectByVisibleText(tipo);
    }

    public void preencherFiltroCodigo(String codigo) {
        $("#codigo").val(codigo);
    }

    public void preencherFiltroSigla(String sigla) {
        $("#sigla").val(sigla);
    }

    public void preencherFiltroMunicipio(String municipio) {
        TestadorDeAutocomplete.buscarESelecionarPrimeiro("idsMunicipio", municipio);
    }

    public void preencherFiltroUf(String uf) {
        $("#uf").as().select().selectByVisibleText(uf);
    }

    public void limparFiltros() {
        preencherFiltroNome("");
        preencherFiltroSituacao("Todos");
        preencherFiltroTipo("Todos");
        preencherFiltroCodigo("");
        preencherFiltroSigla("");
        preencherFiltroUf("Todas");
        preencherFiltroMunicipio("");
    }

    public void clicarNoBotaoAlternarAtivacao(int idEntidade) {
        $("#btnAlternar__" + idEntidade).click();
    }
}
