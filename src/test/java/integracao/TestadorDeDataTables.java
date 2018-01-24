package integracao;

import br.gov.cgu.utils.DateUtils;
import br.gov.cgu.utils.ValorUtils;
import io.github.seleniumquery.SeleniumQueryObject;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebElement;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestadorDeDataTables {

    /**
     * Verifica se, dado um filtro, todas células da coluna possuem o texto em seu conteúdo
     */
    public static void assertColunaFiltradaPorTexto(String idTabela, String tituloColuna, String filtro) {
        int indiceColuna = getIndiceColunaPorTitulo(idTabela, tituloColuna);
        indiceColuna++; // o nth-child() Não é zero-based

        SeleniumQueryObject celulasDaColuna = $("#" + idTabela + " tr td:nth-child(" + indiceColuna + ")");
        for (WebElement celula : celulasDaColuna) {
            assertThat(celula.getText().toLowerCase(), containsString(filtro.toLowerCase()));
        }

    }

    /**
     * Verifica se, dado um filtro, todas células da coluna possuem o data no intervalo informado
     */
    public static void assertColunaDataFiltradaPorIntervalo(String idTabela, String tituloColuna, String filtroDe,String filtroAte) {
        int indiceColuna = getIndiceColunaPorTitulo(idTabela, tituloColuna);
        DateTimeFormatter YYYYMMDD_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        indiceColuna++; // o nth-child() Não é zero-based
        SeleniumQueryObject celulasDaColuna = $("#" + idTabela + " tr td:nth-child(" + indiceColuna + ")");
        LocalDate filtroDeDate;
        LocalDate filtroDeAte;
        for (WebElement celula : celulasDaColuna) {
            filtroDeDate = LocalDate.parse(filtroDe,YYYYMMDD_FORMATTER);
            assertThat(DateUtils.parseLocalDate(celula.getText().substring(0, 10)), is(greaterThanOrEqualTo(filtroDeDate)));
            if(filtroAte!=null){
                filtroDeAte = LocalDate.parse(filtroAte, YYYYMMDD_FORMATTER);
                assertThat(DateUtils.parseLocalDate(celula.getText().substring(0,10)), is(lessThanOrEqualTo(filtroDeAte)));
            }
        }
    }


    /**
     * Ordena uma tabela buscando por uma coluna com o título especificado de maneira ASCENDENTE.
     */
    public static void ordenarPorColunaAscendente(String idTabela, String tituloColuna) {
        int indiceColuna = getIndiceColunaPorTitulo(idTabela, tituloColuna);
        indiceColuna++; // o nth-child() Não é zero-based
        SeleniumQueryObject thDaColuna = $("#" + idTabela + " tr th:nth-child(" + indiceColuna + ")");
        thDaColuna.click();
        esperarDataTableCarregar(idTabela);
        if (!thDaColuna.hasClass("sorting_asc")) {
            thDaColuna.click();
            esperarDataTableCarregar(idTabela);
        }

    }

    /**
     * Ordena uma tabela buscando por uma coluna com o título especificado de maneira DESCENDENTE.
     */
    public static void ordenarPorColunaDescendente(String idTabela, String tituloColuna) {
        int indiceColuna = getIndiceColunaPorTitulo(idTabela, tituloColuna);
        indiceColuna++; // o nth-child() Não é zero-based
        SeleniumQueryObject thDaColuna = $("#" + idTabela + " tr th:nth-child(" + indiceColuna + ")");
        thDaColuna.click();
        esperarDataTableCarregar(idTabela);
        if (!thDaColuna.hasClass("sorting_desc")) {
            thDaColuna.click();
            esperarDataTableCarregar(idTabela);
        }
    }

    /**
     * Verifica se a tabela esta ordenada corretamente pela coluna de texto
     */
    public static void assertOrdenadoAscendentePorColunaTexto(String idTabela, String tituloColuna) {
        int indiceColuna = getIndiceColunaPorTitulo(idTabela, tituloColuna);
        indiceColuna++;// o nth-child() Não é zero-based

        SeleniumQueryObject celulasDaColuna = $("#" + idTabela + " tr td:nth-child(" + indiceColuna + ")");
        String anterior = null;
        for (WebElement celula : celulasDaColuna) {
            if (anterior != null) {
                assertThat(celula.getText(), is(greaterThanOrEqualTo(anterior)));
            }
            anterior = celula.getText();
        }
    }

    /**
     * Verifica se a tabela esta ordenada corretamente pela coluna de date time
     */
    public static void assertOrdenadoAscendentePorColunaData(String idTabela, String tituloColuna) {
        int indiceColuna = getIndiceColunaPorTitulo(idTabela, tituloColuna);
        indiceColuna++;// o nth-child() Não é zero-based

        SeleniumQueryObject celulasDaColuna = $("#" + idTabela + " tr td:nth-child(" + indiceColuna + ")");
        String anterior = null;
        LocalDateTime anteriorDateTime = null;
        for (WebElement celula : celulasDaColuna) {
            if (anterior != null) {
                if(StringUtils.isNotEmpty(celula.getText()) && StringUtils.isNotEmpty(anterior)) {
                    anteriorDateTime = DateUtils.parseLocalDateTime(anterior);
                    assertThat(DateUtils.parseLocalDateTime(celula.getText()), is(greaterThanOrEqualTo(anteriorDateTime)));
                }
            }
            anterior = celula.getText();
        }
    }

    /**
     * Verifica se a tabela esta ordenada corretamente pela coluna de texto
     */
    public static void assertOrdenadoDescendentePorColunaTexto(String idTabela, String tituloColuna) {
        int indiceColuna = getIndiceColunaPorTitulo(idTabela, tituloColuna);
        indiceColuna++;// o nth-child() Não é zero-based

        SeleniumQueryObject celulasDaColuna = $("#" + idTabela + " tr td:nth-child(" + indiceColuna + ")");
        String anterior = null;
        for (WebElement celula : celulasDaColuna) {
            if (anterior != null) {
                assertThat(celula.getText(), is(lessThanOrEqualTo(anterior)));
            }
            anterior = celula.getText();
        }
    }

    /**
     * Verifica se a tabela esta ordenada corretamente pela coluna de date time
     */
    public static void assertOrdenadoDescendentePorColunaData(String idTabela, String tituloColuna) {
        int indiceColuna = getIndiceColunaPorTitulo(idTabela, tituloColuna);
        indiceColuna++;// o nth-child() Não é zero-based

        SeleniumQueryObject celulasDaColuna = $("#" + idTabela + " tr td:nth-child(" + indiceColuna + ")");
        String anterior = null;
        LocalDateTime anteriorDateTime;
        for (WebElement celula : celulasDaColuna) {
            if (anterior != null) {
                if(StringUtils.isNotEmpty(celula.getText()) && StringUtils.isNotEmpty(anterior)) {
                    anteriorDateTime = DateUtils.parseLocalDateTime(anterior);
                    assertThat(DateUtils.parseLocalDateTime(celula.getText()), is(lessThanOrEqualTo(anteriorDateTime)));
                }
            }
            anterior = celula.getText();
        }
    }

    /**
     * Verifica se alguma célula na tabela tem o valor especificado
     */
    public static void assertExisteCelulaComValor(String idTabela, String valor) {
        SeleniumQueryObject celulasDaTabela = $("#" + idTabela + " tr td");
        boolean achou = false;
        for (WebElement celula : celulasDaTabela) {
            if (celula.getText().equals(valor)) {
                achou = true;
                break;
            }
        }
        assertThat(achou, is(true));
    }

    /**
     * Verifica se alguma linha da tabela contém os valores exatamente na ordem em que são passados.
     */
    public static void assertExisteLinhaComValores(String idTabela, String... valores) {
        SeleniumQueryObject linhasDaTabela = $("#" + idTabela + " tr");
        boolean achou = false;
        for (int i = 0; i < linhasDaTabela.size(); i++) {
            SeleniumQueryObject celulasDaLinha = $("#" + idTabela + " tr:eq(" + i + ") td");

            boolean valoresDaLinhaBatem = true;
            for (int j = 0; j < valores.length; j++) {
                String valorDaCelula;
                SeleniumQueryObject checkboxNaCelula = celulasDaLinha.eq(j).find("input[type=checkbox]");
                if (checkboxNaCelula.size() == 1) {
                    valorDaCelula = "" + checkboxNaCelula.is(":checked");
                } else {
                    valorDaCelula = celulasDaLinha.eq(j).text();
                }

                if (!valorDaCelula.equals(valores[j])) {
                    valoresDaLinhaBatem = false;
                }
            }

            if (valoresDaLinhaBatem) {
                achou = true;
                break;
            }
        }

        assertThat(achou, is(true));
    }

    /**
     * Clica no elemento especificado que esteja dentro da Tabela e na Linha determinada
     */
    public static void clicarEmElementoDaLinhaNaTabela(String idTabela, int linha, final String elemento) {
        $("#" + idTabela + " tr:eq(" + linha + ") " + elemento).waitViewClick();
    }

    /**
     * Aguarda até que o datatables esteja totalmente carregado, inclusive as requisições ajax
     */
    public static void esperarDataTableCarregar(String idTabela) {
        $("#" + idTabela + "_processing").waitUntil().is(":hidden");
    }

    /**
     * Testa ordenação de uma coluna de texto completamente.
     */
    public static void testarOrdenacaoDeColunaTextoAscDesc(String idTabela, String tituloColuna) {
        ordenarPorColunaAscendente(idTabela, tituloColuna);
        assertOrdenadoAscendentePorColunaTexto(idTabela, tituloColuna);

        ordenarPorColunaDescendente(idTabela, tituloColuna);
        assertOrdenadoDescendentePorColunaTexto(idTabela, tituloColuna);
    }

    /**
     * Testa ordenação de uma coluna de Data completamente.
     */
    public static void testarOrdenacaoDeColunaDataAscDesc(String idTabela, String tituloColuna) {
        ordenarPorColunaAscendente(idTabela, tituloColuna);
        assertOrdenadoAscendentePorColunaData(idTabela, tituloColuna);

        ordenarPorColunaDescendente(idTabela, tituloColuna);
        assertOrdenadoDescendentePorColunaData(idTabela, tituloColuna);
    }

    private static SeleniumQueryObject getDivInformacoesRegistrosDaTabela(String nomeTabela) {
        SeleniumQueryObject divInformacoesRegistrosDaTabela = $("#" + nomeTabela + "_info");
        if (divInformacoesRegistrosDaTabela.size() == 0) {
            throw new RuntimeException("A div com informações de registros do DataTable não foi encontrada na página.");
        }
        return divInformacoesRegistrosDaTabela;

    }

    private static int getIndiceColunaPorTitulo(String idTabela, String tituloColuna) {
        SeleniumQueryObject colunas = $("#" + idTabela + " th");
        for (int i = 0; i < colunas.size(); i++) {
            if (colunas.get(i).getText().equalsIgnoreCase(tituloColuna)) {
                return i;
            }
        }
        throw new RuntimeException("Coluna com o título '" + tituloColuna + "' não foi encontrada");
    }

    public static void assertOrdenadoAscendentePorColunaValor(String idTabela, String tituloColuna) {
        int indiceColuna = getIndiceColunaPorTitulo(idTabela, tituloColuna);
        indiceColuna++;// o nth-child() Não é zero-based

        SeleniumQueryObject celulasDaColuna = $("#" + idTabela + " tr td:nth-child(" + indiceColuna + ")");
        BigDecimal anterior = null;
        for (WebElement celula : celulasDaColuna) {
            BigDecimal atual = ValorUtils.getValorMonetario(celula.getText());
            if (anterior != null && StringUtils.isNotEmpty(celula.getText())) {
                assertThat(atual, is(greaterThanOrEqualTo(anterior)));
            }
            anterior = atual;
        }
    }

    public static void assertOrdenadoDescendentePorColunaValor(String idTabela, String tituloColuna) {
        int indiceColuna = getIndiceColunaPorTitulo(idTabela, tituloColuna);
        indiceColuna++;// o nth-child() Não é zero-based

        SeleniumQueryObject celulasDaColuna = $("#" + idTabela + " tr td:nth-child(" + indiceColuna + ")");
        BigDecimal anterior = null;
        for (WebElement celula : celulasDaColuna) {
            BigDecimal atual = ValorUtils.getValorMonetario(celula.getText());
            if (anterior != null && StringUtils.isNotEmpty(celula.getText())) {
                assertThat(atual, is(lessThanOrEqualTo(anterior)));
            }
            anterior = atual;
        }
    }

    public static void testarOrdenacaoDeColunaValorAscDesc(String idTabela, String tituloColuna) {
        ordenarPorColunaAscendente(idTabela, tituloColuna);
        assertOrdenadoAscendentePorColunaValor(idTabela, tituloColuna);

        ordenarPorColunaDescendente(idTabela, tituloColuna);
        assertOrdenadoDescendentePorColunaValor(idTabela, tituloColuna);
    }

    public static void testarOrdenacaoDeColunaCodigoEDescricaoAscDesc(String idTabela, String tituloColuna) {
        ordenarPorColunaAscendente(idTabela, tituloColuna);
        assertOrdenadoAscendentePorColunaCodigoEDescricao(idTabela, tituloColuna);

        ordenarPorColunaDescendente(idTabela, tituloColuna);
        assertOrdenadoDescendentePorColunaCodigoEDescricao(idTabela, tituloColuna);
    }

    private static void assertOrdenadoDescendentePorColunaCodigoEDescricao(String idTabela, String tituloColuna) {
        int indiceColuna = getIndiceColunaPorTitulo(idTabela, tituloColuna);
        indiceColuna++;// o nth-child() Não é zero-based

        SeleniumQueryObject celulasDaColuna = $("#" + idTabela + " tr td:nth-child(" + indiceColuna + ")");
        String anterior = null;
        for (WebElement celula : celulasDaColuna) {
            String[] codigoEDescricao = celula.getText().split(" - ");
            String descricao = codigoEDescricao[1];
            if (anterior != null) {
                assertThat(descricao, is(lessThanOrEqualTo(anterior)));
            }
            anterior = descricao;
        }
    }

    private static void assertOrdenadoAscendentePorColunaCodigoEDescricao(String idTabela, String tituloColuna) {
        int indiceColuna = getIndiceColunaPorTitulo(idTabela, tituloColuna);
        indiceColuna++;// o nth-child() Não é zero-based

        SeleniumQueryObject celulasDaColuna = $("#" + idTabela + " tr td:nth-child(" + indiceColuna + ")");
        String anterior = null;
        for (WebElement celula : celulasDaColuna) {
            String[] codigoEDescricao = celula.getText().split(" - ");
            String descricao = codigoEDescricao[1];
            if (anterior != null) {
                assertThat(descricao, is(greaterThanOrEqualTo(anterior)));
            }
            anterior = descricao;
        }
    }
}
