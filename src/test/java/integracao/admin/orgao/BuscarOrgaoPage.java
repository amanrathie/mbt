package integracao.admin.orgao;

import integracao.AdminPage;

import static io.github.seleniumquery.SeleniumQuery.$;

public class BuscarOrgaoPage extends AdminPage {

    public static BuscarOrgaoPage ir() {
        return ir("auth/orgao", BuscarOrgaoPage.class); }

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

    public void limparFiltros() {
        preencherFiltroNome("");
        preencherFiltroSituacao("Todos");
        preencherFiltroTipo("Todos");
        preencherFiltroCodigo("");
        preencherFiltroSigla("");
    }

    public void clicarNoBotaoAlternarAtivacao(int idEntidade) {
        $("#btnAlternar__" + idEntidade).click();
    }
}
