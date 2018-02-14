package br.gov.cgu.mbt.aplicacao.usuario;

import org.springframework.stereotype.Repository;

import br.gov.cgu.mbt.negocio.auth.Usuario;
import br.gov.cgu.persistencia.jpa.RepositoryJpa;

@Repository
public class UsuarioRepository extends RepositoryJpa<Usuario, Integer> {
  
}
