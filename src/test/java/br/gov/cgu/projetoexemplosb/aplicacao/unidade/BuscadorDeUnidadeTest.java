package br.gov.cgu.projetoexemplosb.aplicacao.unidade;

import br.gov.cgu.projetoexemplosb.aplicacao.auth.BuscadorDeUsuario;
import br.gov.cgu.projetoexemplosb.negocio.auth.Usuario;
import br.gov.cgu.projetoexemplosb.negocio.unidade.Unidade;
import br.gov.cgu.projetoexemplosb.negocio.unidade.UnidadeRepository;
import br.gov.cgu.persistencia.querybuilder.RespostaConsulta;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BuscadorDeUnidadeTest {

    private UnidadeQueryBuilder queryBuilder = mock(UnidadeQueryBuilder.class);
    private UnidadeRepository repository = mock(UnidadeRepository.class);
    private BuscadorDeUsuario buscadorDeUsuario = mock(BuscadorDeUsuario.class);

    private BuscadorDeUnidade buscador = new BuscadorDeUnidade(queryBuilder, repository, buscadorDeUsuario);

    @Test
    public void buscar__deve_retornar_resultado_do_queryBuilder() throws Exception {
        //given
        UnidadeFiltro filtro = new UnidadeFiltro();
        RespostaConsulta<UnidadeDTO> respostaConsulta = new RespostaConsulta<>(Collections.emptyList(), 0L);
        when(queryBuilder.build(filtro)).thenReturn(respostaConsulta);

        //when
        RespostaConsulta<UnidadeDTO> resposta = buscador.buscar(filtro);

        //then
        assertThat(resposta, is(respostaConsulta));
    }

    @Test
    public void getParaEdicao__deve_retornar_resultado_do_repository() throws Exception {
        // given
        Integer id = 10;
        Unidade unidadeRepository = new Unidade();
        when(repository.getEagerLoaded(id)).thenReturn(unidadeRepository);

        // when
        Unidade unidade = buscador.getParaEdicao(id);

        // then
        assertThat(unidade, is(unidadeRepository));
    }

    @Test
    public void getPorNomeOuCodigoOuSigla__deve_retornar_resultado_do_repository() throws Exception {
        //given
        String q = "lala";
        List<Unidade> respostaConsulta = new ArrayList<>();
        when(repository.getPorTermo(q)).thenReturn(respostaConsulta);

        //when
        List<Unidade> resposta = buscador.getPorNomeOuCodigoOuSigla(q);

        //then
        assertSame(resposta, respostaConsulta);
    }

    @Test
    public void getPorIds__deve_retornar_resultado_do_repository() throws Exception {
        // given
        List<Integer> ids = new ArrayList<>();
        ids.add(1);
        
        List<Unidade> unidadesRepository = new ArrayList<>();
        when(repository.getPorIds(ids)).thenReturn(unidadesRepository);

        // when
        List<Unidade> unidades = buscador.getPorIds(ids);

        // then
        assertSame(unidades, unidadesRepository);
    }

    @Test
    public void isUsuarioLogadoGestorDaUnidade__deve_retornar_true_se_usuario_gestor() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setId(1);
        when(buscadorDeUsuario.getUsuarioLogado()).thenReturn(usuario);
        when(repository.isGestor(anyInt(), anyInt())).thenReturn(Boolean.TRUE);

        boolean compartilhado = buscador.isUsuarioLogadoGestorDaUnidade(1);

        assertThat(compartilhado, is(Boolean.TRUE));
    }

    @Test
    public void isUsuarioLogadoGestorDaUnidade__deve_retornar_false_se_usuario_nao_gestor() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setId(1);
        when(buscadorDeUsuario.getUsuarioLogado()).thenReturn(usuario);
        when(repository.isGestor(anyInt(), anyInt())).thenReturn(Boolean.FALSE);

        boolean compartilhado = buscador.isUsuarioLogadoGestorDaUnidade(1);

        assertThat(compartilhado, is(Boolean.FALSE));
    }

    @Test
    public void isUsuarioLogadoGestorDaUnidade__deve_retornar_false_se_idUnidade_nao_fornecido() throws Exception {
        boolean compartilhado = buscador.isUsuarioLogadoGestorDaUnidade(null);

        assertThat(compartilhado, is(Boolean.FALSE));
    }
}