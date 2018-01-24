package integracao.admin.unidade;

import integracao.AdminPage;
import integracao.TestadorDeAutocomplete;

import static io.github.seleniumquery.SeleniumQuery.$;

public class CadastrarUnidadePage extends AdminPage {

    public static CadastrarUnidadePage ir() { return ir("auth/unidade/novo", CadastrarUnidadePage.class); }
    public static CadastrarUnidadePage ir(int id) { return ir("auth/unidade/" + id, CadastrarUnidadePage.class); }

    public String getValoresDaComboTipos() {
        return $("#tipo option").text();
    }


    public String getCampoNome() {
        return $("#nome").val();
    }

    public void preencherTipo(String s) {
        $("#tipo").as().select().selectByVisibleText(s);
    }

    public void preencherNome(String nome) {
        $("#nome").val(nome);
    }

    public void preencherCodigo(String codigo) {
        $("#codigo").val(codigo);
    }

    public void preencherSigla(String sigla) {
        $("#sigla").val(sigla);
    }

    public void preencherEmail(String s) {
        $("#email").val(s);
    }

    public void preencherTelefone(String s) {
        $("#telefone").val(s);
    }

    public void clicarEmSalvar() {
        $("#btnSalvar").click();
    }

    public void preencherMunicipio(String valor) {
        TestadorDeAutocomplete.buscarESelecionarPrimeiro("idMunicipio", valor);
    }

    public String getCampoTipo() {
        return getValorSelecionadoDeCombo("tipo");
    }

    public String getCampoCodigo() {
        return $("#codigo").val();
    }

    public String getCampoSigla() {
        return $("#sigla").val();
    }

    public String getCampoUnidadeSuperior() {
        return $("#idUnidadeSuperior").val();
    }

    public String getCampoOrgao() {
        return $("#idOrgao").val();
    }

    public String getCampoEmail() {
        return $("#email").val();
    }

    public String getCampoTelefone() {
        return $("#telefone").val();
    }

    public String getCampoMunicipio() {
        return $("#idMunicipio").val();
    }

    public String getCampoGestores() {
        return $("#gestores").val();
    }
}
