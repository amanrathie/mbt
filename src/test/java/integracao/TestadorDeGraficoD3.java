package integracao;

import io.github.seleniumquery.SeleniumQueryObject;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public final class TestadorDeGraficoD3 {

    /**
     * Verifica se um dado grafico Pizza possui o titulo e o tamanho correto de fatias.
     */
    public static void assertGraficoPizza(String idGrafico, String titulo, String... fatias) {
        SeleniumQueryObject grafico = $("#" + idGrafico);
        assertThat("Titulo do gráfico diferente do esperado", grafico.find(".box-grafico-pizza__label").text(), is (titulo));

        SeleniumQueryObject slices = grafico.find(".grafico-pizza .slice");
        assertThat("Quantidade de fatias diferente da esperada!", slices.size(), is(fatias.length));
        for (int i = 0; i < fatias.length; i++) {
            assertThat("Fatia " + i + " do grafico com tamanho diferente do esperado.", slices.get(i).getText(), is(fatias[i]));
        }
    }

    /**
     * Clica numa barra (indice) específica do gráfico de barras
     */
    public static void clickGraficoBarras(String idGrafico, int indice) {
        $($("#" + idGrafico + " .grafico-receptor .bar.clicavel").get(indice)).click();
    }

    /**
     * Verifica o comportamento do Toggler de visibilidade da tabela referente ao grafico
     */
    public static void assertTogglerTabelaDoGrafico(String idGrafico, String idTabela) {
        assertThat("Tabela referente ao gráfico deveria começar escondida!", $("#" + idTabela).is(":hidden"), is(true));
        $("#" + idGrafico + " .grafico-tabela__botao-collapse").click();
        assertThat("Tabela referente ao gráfico não foi exibida!", $("#" + idTabela).is(":visible"), is(true));
    }

    public static void clickGraficoPizza(String idGrafico, int fatia) {
        $("#" + idGrafico).find(".fatia.clicavel").get(fatia).click();
    }
}
