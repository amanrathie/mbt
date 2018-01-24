package integracao;

import org.openqa.selenium.WebElement;

import java.util.List;

import static io.github.seleniumquery.SeleniumQuery.$;

public class TestadorDeAutocomplete {

    /**
     * @param id id do formulário puro, sem o token input. Ex: "instituicaoContratante.id"
     * @param nomePesquisado nome que será digitado no autocomplete
     * @param parteDoNomeQueDeveSerSelecionado parte do nome que aparecerá no autocomplete e deve ser clicado
     */
    public static void buscarESelecionar(String id, String nomePesquisado, String parteDoNomeQueDeveSerSelecionado) {
        inserirTextoPesquisa(id, nomePesquisado);
        $("div.token-input-dropdown").waitUntil().text().contains(parteDoNomeQueDeveSerSelecionado);
        $("div.token-input-dropdown li:contains('" + parteDoNomeQueDeveSerSelecionado + "')").click();
    }


    
    public static void buscarESelecionarPrimeiro(String id, String nomePesquisado) {
        inserirTextoPesquisa(id, nomePesquisado);
        $("div.token-input-dropdown").waitUntil().text().contains(nomePesquisado);

        for (WebElement w : $("div.token-input-dropdown").get()) {
            if (!w.getText().trim().isEmpty()) {
                w.click();
                break;
            }
        }
    }

    public static void inserirTextoPesquisa(String id, String valor) {
        String idTokenInput = "//*[@id='token-input-" + id + "']" ;
        $(idTokenInput).val(valor);
    }

    public static String recuperarSelecionadosNoAutocomplete(String selector) {
        StringBuilder textoCompleto = new StringBuilder();
        List<WebElement> elements = $("#" + selector).parent().find("ul.token-input-list li.token-input-token p").get();

        for (WebElement element : elements) {
            textoCompleto.append("; " + $(element).html().replaceAll("\\<.*?>", "").trim());
        }

        return textoCompleto.length() > 2 ? textoCompleto.substring(2, textoCompleto.length()) : "";
    }
}
