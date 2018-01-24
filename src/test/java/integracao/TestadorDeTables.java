package integracao;

import org.apache.commons.lang3.StringUtils;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.text.IsEqualIgnoringWhiteSpace.equalToIgnoringWhiteSpace;

public class TestadorDeTables {

    public static void assertLinhaDaTabela(String id, int linha, String... celulas) {
        assertThat($("#" + id + " tbody tr:nth-child(" + linha + ")").text(),
                   is(equalToIgnoringWhiteSpace(StringUtils.join(celulas, " "))));
    }

    public static int getQuantidadeRegistrosTabela(String id) {
        return $("#"+id+" tbody").find("tr").size();
    }
}
