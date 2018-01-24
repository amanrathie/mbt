package integracao;

import org.openqa.selenium.support.PageFactory;

import static io.github.seleniumquery.SeleniumQuery.$;

/**
 * Classe base para as WebDriver Pages
 */
public abstract class AdminPage {

    public static final String MENSAGEM_ERRO_CAMPO_OBRIGATORIO = "Obrigatório";
    public static String URL_BASE = "http://localhost:8080/";

    public static void esperarLoadingsSumirem() {
        $("#carregando").waitUntil().is(":hidden");
        if ($(".dataTables_processing").size() > 0) {
            $(".dataTables_processing").waitUntil().is(":hidden");
        }
    }

    protected static <T extends AdminPage> T ir(String url, Class<T> pageClass) {
        $.url(URL_BASE + url);
        if ($.url().contains("/adfs/ls")) {
            throw new RuntimeException("Faça o login antes de chamar a URL!");
        }
        return PageFactory.initElements($.driver().get(), pageClass);
    }

    public static void fazerLoginComTodasPermissoes() {
        fazerLogin("CGU\\USER_CGSIS_TESTE_01", "CGsis*(AD)*");
    }

    public static void fazerLoginSoComoAnalistaCGU() {
        fazerLogin("CGU\\USER_CGTEC_TESTE_01", "CGsis*(AD)*");
    }

    public static void fazerLogin(String usuario, String password) {
        $.url(URL_BASE + "login");
        $("#username").val(usuario);
        $("#btnSubmit").click();
    }

    public String getMensagemDeErroDoCampo(String campo) {
        return $("#" + campo + "-error").text();
    }

    public String getMensagemDeSucesso() {
        return $(".toast-success .toast-message").waitUntil().is(":visible").then().text().trim();
    }

    public String getMensagemDeAlerta() {
        return $(".toast-warning .toast-message").waitUntil().is(":visible").then().text().trim();
    }

    public String getMensagemDeErro() {
        return $(".toast-success .toast-message").text().trim();
    }

    public void expandirFiltros() {
        $(".botao-filtros").click();
        $("#btnFiltrar").waitUntil().is(":visible");
    };

    public void clicarNoLinkUsuarioLogado() { $("#linkUsuarioLogado").click(); }

    public String getValorSelecionadoDeCombo(final String id) {
        return $("#" + id + " option").filter(":selected").text();
    }

    public void selecionarValorEmCombo(final String id, String valor) {
        $("#" + id).as().select().selectByVisibleText(valor);
    }

    protected String getConteudoTabela(final String id) {
        return $("#" + id + " tbody").text().replace("\n"," ");
    }
}
