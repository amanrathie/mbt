package br.gov.cgu.mbt.aplicacao.entidadeavaliada;

import org.springframework.stereotype.Repository;

import br.gov.cgu.mbt.negocio.entidadeavaliada.Localidade;
import br.gov.cgu.persistencia.jpa.RepositoryJpa;

@Repository
public class LocalidadeRepository extends RepositoryJpa<Localidade, Integer> {

}
