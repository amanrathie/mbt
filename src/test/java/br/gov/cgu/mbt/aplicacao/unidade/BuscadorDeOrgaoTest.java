package br.gov.cgu.mbt.aplicacao.unidade;

import br.gov.cgu.mbt.aplicacao.unidade.BuscadorDeOrgao;
import br.gov.cgu.mbt.aplicacao.unidade.OrgaoDTO;
import br.gov.cgu.mbt.aplicacao.unidade.OrgaoFiltro;
import br.gov.cgu.mbt.aplicacao.unidade.OrgaoQueryBuilder;
import br.gov.cgu.mbt.negocio.unidade.Orgao;
import br.gov.cgu.mbt.negocio.unidade.OrgaoRepository;
import br.gov.cgu.persistencia.querybuilder.RespostaConsulta;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BuscadorDeOrgaoTest {

    private OrgaoQueryBuilder queryBuilder = mock(OrgaoQueryBuilder.class);
    private OrgaoRepository repository = mock(OrgaoRepository.class);

    private BuscadorDeOrgao buscador = new BuscadorDeOrgao(queryBuilder, repository);

    @Test
    public void buscar__deve_retornar_resultado_do_queryBuilder() throws Exception {
        //given
        OrgaoFiltro filtro = new OrgaoFiltro();
        RespostaConsulta<OrgaoDTO> respostaConsulta = new RespostaConsulta<>(Collections.emptyList(), 0L);
        when(queryBuilder.build(filtro)).thenReturn(respostaConsulta);

        //when
        RespostaConsulta<OrgaoDTO> resposta = buscador.buscar(filtro);

        //then
        assertThat(resposta, is(respostaConsulta));
    }

    @Test
    public void getPorNomeOuCodigoOuSigla__deve_retornar_resultado_do_repository() throws Exception {
        //given
        String q = "lala";
        List<Orgao> respostaConsulta = new ArrayList<>();
        when(repository.getPorTermo(q)).thenReturn(respostaConsulta);

        //when
        List<Orgao> resposta = buscador.getPorNomeOuCodigoOuSigla(q);

        //then
        assertSame(resposta, respostaConsulta);
    }

    @Test
    public void getParaEdicao__deve_retornar_resultado_do_repository() throws Exception {
        // given
        Integer id = 10;
        Orgao orgaoRepository = new Orgao();
        when(repository.getEagerLoaded(id)).thenReturn(orgaoRepository);

        // when
        Orgao orgao = buscador.getParaEdicao(id);

        // then
        assertThat(orgao, is(orgaoRepository));
    }

    @Test
    public void getPorIds__deve_retornar_resultado_do_repository() throws Exception {
        // given
        List<Integer> ids = new ArrayList<>();
        ids.add(1);
        
        List<Orgao> orgaosRepository = new ArrayList<>();
        when(repository.getPorIds(ids)).thenReturn(orgaosRepository);

        // when
        List<Orgao> orgaos = buscador.getPorIds(ids);

        // then
        assertSame(orgaos, orgaosRepository);
    }
}