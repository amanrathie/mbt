package br.gov.cgu.mbt.aplicacao.unidade;

import br.gov.cgu.mbt.negocio.unidade.Orgao;
import br.gov.cgu.mbt.negocio.unidade.OrgaoRepository;
import br.gov.cgu.persistencia.querybuilder.RespostaConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuscadorDeOrgao {

    private final OrgaoQueryBuilder queryBuilder;
    private final OrgaoRepository repository;

    @Autowired
    public BuscadorDeOrgao(OrgaoQueryBuilder queryBuilder, OrgaoRepository repository) {
        this.queryBuilder = queryBuilder;
        this.repository = repository;
    }

    public RespostaConsulta<OrgaoDTO> buscar(OrgaoFiltro filtro) {
        return queryBuilder.build(filtro);
    }

    public List<Orgao> getPorNomeOuCodigoOuSigla(String q) {
        return repository.getPorTermo(q);
    }

    public Orgao getParaEdicao(Integer id) {
        return repository.getEagerLoaded(id);
    }

    public List<Orgao> getPorIds(List<Integer> ids) {
        return repository.getPorIds(ids);
    }
}
