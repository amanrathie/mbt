package br.gov.cgu.mbt.aplicacao.auth;

import br.gov.cgu.mbt.negocio.auth.Usuario;
import br.gov.cgu.mbt.negocio.auth.UsuarioRepository;
import br.gov.cgu.mbt.negocio.unidade.Unidade;
import br.gov.cgu.persistencia.querybuilder.RespostaConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuscadorDeUsuario {

    private final UsuarioQueryBuilder queryBuilder;
    private final UsuarioRepository repository;

    @Autowired
    public BuscadorDeUsuario(UsuarioQueryBuilder queryBuilder, UsuarioRepository repository) {
        this.queryBuilder = queryBuilder;
        this.repository = repository;
    }

    public RespostaConsulta<UsuarioDTO> buscar(UsuarioFiltro filtro) {
        return queryBuilder.build(filtro);
    }
    
    public Usuario getParaEdicao(Integer id) {
        return repository.getEagerLoaded(id);
    }

    public Usuario getUsuarioLogado() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof Usuario) {
                return (Usuario) principal;
            }
        }
        return null;
    }

    public List<Unidade> buscarHierarquiaUnidadesUsuarioLogado() {
        return repository.buscarHierarquiaUnidadesUsuario(getUsuarioLogado());
    }

    public Usuario getUsuarioPorChaveApi(String chaveApi) {
        return repository.getPorChaveApi(chaveApi);
    }

    public Usuario buscarPorLogin(String login) {
        return repository.getPorLogin(login);
    }

    public List<Usuario> getPorIds(List<Integer> ids) {
        return repository.getPorIds(ids);
    }
}
