package br.gov.cgu.mbt.aplicacao.avaliacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.cgu.mbt.aplicacao.avaliacao.questionario.CalculadorQuestionario;
import br.gov.cgu.mbt.aplicacao.avaliacao.questionario.ConversorQuestionario;
import br.gov.cgu.mbt.negocio.avaliacao.Avaliacao;
import br.gov.cgu.mbt.negocio.avaliacao.questionario.Questionario;
import br.gov.cgu.mbt.negocio.avaliacao.questionario.QuestionarioRepository;

@Service
public class PublicadorDeAvaliacao {
	private QuestionarioRepository questionarioRepository;
	private CalculadorQuestionario calculadorQuestionario;
	
	@Autowired
	public PublicadorDeAvaliacao(QuestionarioRepository questionarioRepository,
			CalculadorQuestionario calculadorQuestionario) {
		this.questionarioRepository = questionarioRepository;
		this.calculadorQuestionario = calculadorQuestionario;
	}
	
	// TODO: contem a logica para publicar uma avaliacao. Ou seja, calcular também seu resultado
	public void publicar(Avaliacao avaliacao) {	
		Questionario questionario = questionarioRepository.getEagerLoaded(avaliacao.getQuestionario().getId());
		calculadorQuestionario.calculaNota(ConversorQuestionario.toBlocos(questionario.getEstrutura()));
		
	}

}
