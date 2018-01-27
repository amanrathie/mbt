package br.gov.cgu.mbt.negocio.auth;

import br.gov.cgu.mbt.negocio.auth.Perfil;
import br.gov.cgu.mbt.negocio.auth.PerfilRepository;
import br.gov.cgu.test.entitymanager.InjetarEntityManager;
import br.gov.cgu.test.entitymanager.InjetarEntityManagerRule;
import br.gov.cgu.test.entitymanager.InjetarEntityManagerRuleBuilder;
import infraestrutura.AssertUtils;
import org.junit.Rule;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.google.common.primitives.Ints.asList;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class PerfilRepositoryTest {

    @Rule
    public InjetarEntityManagerRule emRule = InjetarEntityManagerRuleBuilder.profilePadrao().build();

    @InjetarEntityManager
    private PerfilRepository repository = new PerfilRepository();

    @Test
    public void buscar__todos_ordenados_por_nome() throws Exception {
        // Given

        // When
        List<Perfil> perfis = repository.buscarTodosOrdenadosPorNome();

        // Then
        assertThat(perfis, hasSize(4));
        AssertUtils.assertListaOrdenadaPorPropriedade(perfis, Comparator.comparing(o -> o.getNome().toLowerCase()));
    }

    @Test
    public void getPorIds__retorna_perfis_da_lista_informada() throws Exception {
        // Given
        List<Integer> ids = asList(1, 3, 2);

        // When
        List<Perfil> perfis = repository.getPorIds(ids);

        // Then
        assertThat(perfis, hasSize(3));
        for (Perfil p : perfis) {
            assertThat(p.getId(), isIn(ids));
        }

        assertTrue(ids.stream().sorted().collect(Collectors.toList())
                .equals(perfis.stream().sorted(Comparator.comparing(Perfil::getId))
                        .map(Perfil::getId).collect(Collectors.toList())));
    }

    @Test
    public void buscarTodosPublicoExterno__retorna_todos_de_publico_externo() throws Exception {
        // Given

        // When
        List<Perfil> perfis = repository.buscarTodosPublicoExterno();

        // Then
        perfis.forEach(p -> assertTrue(p.isOferecidoPublicoExterno()));
    }

    @Test
    public void getPorNomes__retorna_perfis_da_lista_informada() throws Exception {
        // Given
        List<String> nomesDosPerfis = Arrays.asList("Desenvolvedor DTI", "Gestor Sistema", "Auditor CGU");

        // When
        List<Perfil> perfis = repository.getPorNomes(nomesDosPerfis);

        // Then
        assertThat(perfis, hasSize(3));
        for (Perfil p : perfis) {
            assertThat(p.getNome(), isIn(nomesDosPerfis));
        }

        assertTrue(nomesDosPerfis.stream().sorted().collect(Collectors.toList())
                      .equals(perfis.stream().sorted(Comparator.comparing(Perfil::getNome))
                                    .map(Perfil::getNome).collect(Collectors.toList())));
    }

}