package br.gov.cgu.mbt.aplicacao.avaliacao.questionario;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.cgu.mbt.negocio.avaliacao.questionario.Questionario;

@Service
public class GerenciadorQuestionario {
	
	private QuestionarioRepository repository;

	@Autowired
	public GerenciadorQuestionario(QuestionarioRepository repository) {
		this.repository = repository;
	}
	
	@Transactional
	public Questionario salvar(Questionario questionario) {
		return repository.put(questionario);
	}

}
