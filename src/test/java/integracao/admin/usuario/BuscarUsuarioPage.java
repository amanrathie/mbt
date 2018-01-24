package integracao.admin.usuario;

import static io.github.seleniumquery.SeleniumQuery.$;

import integracao.AdminPage;

public class BuscarUsuarioPage extends AdminPage {

    public static BuscarUsuarioPage ir() { return ir("auth/usuario", BuscarUsuarioPage.class); }

    public void definirSituacaoUsuariosResultado(String situacao) {
       $("#ativo").as().select().selectByVisibleText(situacao);
       
       esperarLoadingsSumirem();
    }

    public String getConteudoTabela() {
        return $("#lista tbody").text().replace("\n"," ");
    }
    
    public void clicarEmFiltrar() {
        $("#btnFiltrar").click();
    }

    public void clicarNoBotaoAlternarAtivacao(int idUsuario) {
        $("#btnAlternar__" + idUsuario).click();
    }
}
