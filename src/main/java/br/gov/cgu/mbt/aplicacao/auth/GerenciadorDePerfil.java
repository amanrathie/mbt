package br.gov.cgu.mbt.aplicacao.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.cgu.mbt.negocio.auth.Perfil;
import br.gov.cgu.mbt.negocio.auth.PerfilRepository;

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
