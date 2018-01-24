package br.gov.cgu.projetoexemplosb.negocio.nucleo;

import br.gov.cgu.test.entitymanager.InjetarEntityManager;
import br.gov.cgu.test.entitymanager.InjetarEntityManagerRule;
import br.gov.cgu.test.entitymanager.InjetarEntityManagerRuleBuilder;
import org.junit.Rule;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class MunicipioRepositoryTest {

    @Rule
    public InjetarEntityManagerRule emRule = InjetarEntityManagerRuleBuilder.profilePadrao().build();

    @InjetarEntityManager
    private MunicipioRepository repository = new MunicipioRepository();

    @Test
    public void getPorTermo____deve_filtrar_por_descricao() {
        // Given
        String termo = "Belo";
        // When
        List<Municipio> municipios  = repository.getPorTermo(termo);

        // Then
        assertThat(municipios, hasSize(greaterThan(0)));
        for (Municipio municipio : municipios) {
            assertThat(municipio.getDescricao(), containsString(termo));
        }
    }

    @Test
    public void getPorIds() throws Exception {
        //given
        List<Integer> ids = Arrays.asList(1, 3, 5);

        //when
        List<Municipio> municipios = repository.getPorIds(ids);

        //then
        assertThat(municipios, hasSize(3));
        for (Municipio municipio : municipios) {
            assertThat(municipio.getId(), isIn(ids));
        }
    }

}