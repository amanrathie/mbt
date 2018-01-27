package br.gov.cgu.mbt.aplicacao.auth;

import org.junit.Test;

import br.gov.cgu.mbt.aplicacao.auth.BuscadorDePerfil;
import br.gov.cgu.mbt.aplicacao.auth.PerfilQueryBuilder;
import br.gov.cgu.mbt.negocio.auth.Perfil;
import br.gov.cgu.mbt.negocio.auth.PerfilRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BuscadorDePerfilTest {

    public static final Perfil ANALISTA_CGU = new Perfil(1, "Analista CGU");
    public static final Perfil ADMINISTRADOR = new Perfil(0, "Administrador");

    private PerfilRepository repository = mock(PerfilRepository.class);
    private PerfilQueryBuilder queryBuilder= mock(PerfilQueryBuilder.class);
    private BuscadorDePerfil buscador = new BuscadorDePerfil(repository, queryBuilder);

    @Test
    public void recuperarTodos__deve_retornar_resultado_do_repository() throws Exception {
        // given
        List<Perfil> perfis = new ArrayList<>();
        perfis.add(ADMINISTRADOR);
        perfis.add(ANALISTA_CGU);

        when(repository.getAll()).thenReturn(perfis);

        // when
        List<Perfil> resposta = buscador.recuperarTodos();

        // then
        assertSame(perfis, resposta);
    }

    @Test
    public void recuperarTodosExcetoAdministrador__deve_retornar_resultado_do_repository() throws Exception {
        // given
        List<Perfil> perfis = new ArrayList<>();
        perfis.add(ANALISTA_CGU);

        when(repository.getAll()).thenReturn(perfis);

        // when
        List<Perfil> resposta = buscador.recuperarTodos();

        // then
        assertSame(perfis, resposta);
    }

    @Test
    public void recuperarTodosPublicoExterno__deve_retornar_resultado_do_repository() throws Exception {
        // given
        List<Perfil> perfis = new ArrayList<>();
        perfis.add(ANALISTA_CGU);

        when(repository.buscarTodosPublicoExterno()).thenReturn(perfis);

        // when
        List<Perfil> resposta = buscador.recuperarTodosPublicoExterno();

        // then
        assertSame(perfis, resposta);
    }

    @Test
    public void recuperarTodosPublicoInterno__deve_retornar_resultado_do_repository() throws Exception {
        // given
        List<Perfil> perfis = new ArrayList<>();
        perfis.add(ANALISTA_CGU);

        when(repository.getAll()).thenReturn(perfis);

        // when
        List<Perfil> resposta = buscador.recuperarTodos();

        // then
        assertSame(perfis, resposta);
    }

}