package br.gov.cgu.mbt.aplicacao.unidade;

import br.gov.cgu.mbt.aplicacao.auth.BuscadorDeUsuario;
import br.gov.cgu.mbt.negocio.unidade.Unidade;
import br.gov.cgu.mbt.negocio.unidade.UnidadeRepository;
import br.gov.cgu.persistencia.querybuilder.RespostaConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuscadorDeUnidade {

    private final UnidadeQueryBuilder queryBuilder;
    private final UnidadeRepository repository;
    private final BuscadorDeUsuario buscadorDeUsuario;

    @Autowired
    public BuscadorDeUnidade(UnidadeQueryBuilder queryBuilder, UnidadeRepository repository, BuscadorDeUsuario buscadorDeUsuario) {
        this.queryBuilder = queryBuilder;
        this.repository = repository;
        this.buscadorDeUsuario = buscadorDeUsuario;
    }

    public RespostaConsulta<UnidadeDTO> buscar(UnidadeFiltro filtro) {
        return queryBuilder.build(filtro);
    }

    public List<Unidade> getPorNomeOuCodigoOuSigla(String q) {
        return repository.getPorTermo(q);
    }

    public Unidade getParaEdicao(Integer id) {
        return repository.getEagerLoaded(id);
    }

    public List<Unidade> getPorIds(List<Integer> ids) {
        return repository.getPorIds(ids);
    }

    public boolean isUsuarioLogadoGestorDaUnidade(Integer idUnidade) {
        if (idUnidade != null) {
            return repository.isGestor(idUnidade, buscadorDeUsuario.getUsuarioLogado().getId());
        } else {
            return false;
        }
    }
}
