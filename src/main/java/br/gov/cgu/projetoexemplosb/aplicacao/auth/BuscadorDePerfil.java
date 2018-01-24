package br.gov.cgu.projetoexemplosb.aplicacao.auth;

import br.gov.cgu.projetoexemplosb.negocio.auth.Perfil;
import br.gov.cgu.projetoexemplosb.negocio.auth.PerfilRepository;
import br.gov.cgu.persistencia.querybuilder.RespostaConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig(cacheNames = "Principal")
public class BuscadorDePerfil {

    private final PerfilRepository repository;
    private final PerfilQueryBuilder queryBuilder;

    @Autowired
    public BuscadorDePerfil(PerfilRepository repository, PerfilQueryBuilder queryBuilder) {
        this.repository = repository;
        this.queryBuilder = queryBuilder;
    }
    
    public List<Perfil> recuperarTodos() {
        return repository.getAll();
    }

    public List<Perfil> recuperarTodosPublicoExterno() {
        return repository.buscarTodosPublicoExterno();
    }

    public RespostaConsulta<PerfilDTO> buscar(PerfilFiltro filtro) {
        return queryBuilder.build(filtro);
    }

    public Perfil getParaEdicao(Integer id) {
        return repository.get(id);
    }
}
