package br.gov.cgu.projetoexemplosb.aplicacao.auth;

import br.gov.cgu.projetoexemplosb.negocio.auth.Perfil;
import br.gov.cgu.projetoexemplosb.negocio.auth.PerfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GerenciadorDePerfil {

    private final PerfilRepository repository;

    @Autowired
    public GerenciadorDePerfil(PerfilRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Perfil salvar(Perfil perfil) {
        return repository.put(perfil);
    }
}
