package infraestrutura;

import br.gov.cgu.persistencia.jpa.SQLTemplatesFactory;
import br.gov.cgu.persistencia.jpa.SQLTemplatesFactory.Banco;
import br.gov.cgu.persistencia.querybuilder.Filtro;
import br.gov.cgu.persistencia.querybuilder.QueryBuilderJPASQL;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.sql.SQLTemplates;
import org.junit.Before;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

public abstract class QueryBuilderTest<F extends Filtro, DTO> {

    @Before
    public void before() {
        System.setProperty("spring.profiles.active", "teste");
        SQLTemplates template = SQLTemplatesFactory.build(Banco.TERADATA);
        ReflectionTestUtils.setField(getQueryBuilder(), "sqlTemplate", template);
    }

    protected void testarQuery(String colunaOrdenacao, F filtro, int qntRegistrosEsperada) {
        List<DTO> resultado;
        resultado = getQueryBuilder()
                .gerarQuery( filtro )
                .orderBy(new OrderSpecifier<>(Order.DESC, getQueryBuilder().getOrderByExpression(colunaOrdenacao)))
                .fetch();

        assertThat("O resultado não tem o tamanho esperado!", resultado, hasSize(qntRegistrosEsperada));
        assertOrdenacao(resultado, colunaOrdenacao);
    }

    protected abstract void assertOrdenacao(List<DTO> resultado, String colunaOrdenacao);


    public abstract QueryBuilderJPASQL<F, DTO> getQueryBuilder();
}
