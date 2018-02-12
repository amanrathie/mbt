package br.gov.cgu.mbt.aplicacao.avaliacao.questionario;

import org.springframework.stereotype.Repository;

import br.gov.cgu.mbt.negocio.avaliacao.questionario.Questionario;
import br.gov.cgu.persistencia.jpa.RepositoryJpa;

@Repository
public class QuestionarioRepository extends RepositoryJpa<Questionario, Integer> {
	
}
