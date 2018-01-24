package integracao.admin.orgao;

import integracao.AdminPage;

import static io.github.seleniumquery.SeleniumQuery.$;

public class CadastrarOrgaoPage extends AdminPage {

    public static CadastrarOrgaoPage ir() { return ir("auth/orgao/novo", CadastrarOrgaoPage.class); }
    public static CadastrarOrgaoPage ir(int id) { return ir("auth/orgao/" + id, CadastrarOrgaoPage.class); }

    String getValoresDaComboTipos() {
        return $("#tipo option").text();
    }

    void preencherTipo(String s) {
        selecionarValorEmCombo("tipo", s);
    }

    void preencherNome(String nome) {
        $("#nome").val(nome);
    }

    void preencherCodigo(String codigo) {
        $("#codigo").val(codigo);
    }

    void preencherSigla(String sigla) {
        $("#sigla").val(sigla);
    }

    void clicarEmSalvar() {
        $("#btnSalvar").click();
    }

    String getCampoNome() {
        return $("#nome").val();
    }

    String getCampoTipo() {
        return getValorSelecionadoDeCombo("tipo");
    }

    String getCampoCodigo() {
        return $("#codigo").val();
    }

    String getCampoSigla() {
        return $("#sigla").val();
    }
}
