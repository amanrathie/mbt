package br.gov.cgu.mbt.aplicacao.avaliacao;

import org.springframework.stereotype.Repository;

import br.gov.cgu.mbt.negocio.avaliacao.Avaliacao;
import br.gov.cgu.persistencia.jpa.RepositoryJpa;

@Repository
public class AvaliacaoRepository extends RepositoryJpa<Avaliacao, Integer> {

}
