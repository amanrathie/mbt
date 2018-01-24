package infraestrutura;

import java.util.Comparator;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public final class AssertUtils {

    /**
     * Exemplo de comparator de ordem crescente
     * (o1, o2) -> o1.getNome().compareTo(o2.getNome())
     * Descrescente
     * (o1, o2) -> o2.getNome().compareTo(o1.getNome())
     */
    public static <T> void assertListaOrdenadaPorPropriedade(List<T> entidades, Comparator<T> comparator) {
        T anterior = null;
        for (T entidade : entidades) {
            if (anterior != null) {
                assertThat("A lista não está ordenada corretamente!",
                           comparator.compare(entidade, anterior), is(greaterThanOrEqualTo(0)));
            }
            anterior = entidade;
        }
    }

    public static void assertColunaPreenchidaCorretamente(List<String> colunasSelecionadas, String coluna, Object valorColuna) {
        if (colunasSelecionadas.contains(coluna)) {
            assertThat("A coluna " + coluna + " deveria ter vindo preenchida!", valorColuna, is(notNullValue()));
        } else {
            assertThat("A coluna " + coluna + " NÃO deveria ter vindo preenchida!", valorColuna, is(nullValue()));
        }
    }
}
