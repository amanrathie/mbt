package integracao;

import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static io.github.seleniumquery.SeleniumQuery.$;

public class TestadorDeMultiselect {

    public static void selecionarNoMultiselect(String seletor, final List<String> values) {
        $(seletor + "_btn-group").click();

        for (String v: values) {
            $(seletor + "_btn-group input[value='" + v + "']").waitUntil().is(":visible").then().click();
        }

        $(seletor + "_btn-group").click();
    }

    public static  List<String> recuperarSelecionadosNoMultiselect(String selector) {
        List<String> selectedElementsDescription = new ArrayList();
        List<WebElement> elements = $("#" + selector + "_btn-group li.active label").get();

        for (WebElement element : elements) {
            selectedElementsDescription.add($(element).html().replaceAll("\\<.*?>","").trim());
        }

        return selectedElementsDescription;
    }

    public static  String recuperarTextoDosSelecionadosNoMultiselect(String selector) {
        StringBuilder textoCompleto = new StringBuilder();
        List<WebElement> elements = $("#" + selector + "_btn-group li.active label").get();

        for (WebElement element : elements) {
            textoCompleto.append("; " + $(element).html().replaceAll("\\<.*?>","").trim());
        }

        return textoCompleto.length() > 2 ? textoCompleto.substring(2, textoCompleto.length()) : "";
    }
}
