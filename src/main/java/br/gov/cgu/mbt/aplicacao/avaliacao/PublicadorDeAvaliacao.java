package br.gov.cgu.mbt.aplicacao.avaliacao;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.cgu.mbt.aplicacao.avaliacao.questionario.ConversorQuestionario;
import br.gov.cgu.mbt.aplicacao.avaliacao.questionario.calculador.CalculadorQuestionario;
import br.gov.cgu.mbt.negocio.avaliacao.Avaliacao;
import br.gov.cgu.mbt.negocio.avaliacao.questionario.Questionario;
import br.gov.cgu.mbt.negocio.avaliacao.questionario.RespostaQuestionario;
import br.gov.cgu.mbt.negocio.avaliacao.resultado.ResultadoAvaliacao;
import br.gov.cgu.mbt.negocio.avaliacao.resultado.ResultadoAvaliacaoRepository;

@Service
public class PublicadorDeAvaliacao {
	private CalculadorQuestionario calculadorQuestionario;
	private ResultadoAvaliacaoRepository resultadoAvaliacaoRepository;
	
	@Autowired
	public PublicadorDeAvaliacao(CalculadorQuestionario calculadorQuestionario,
			ResultadoAvaliacaoRepository resultadoAvaliacaoRepository) {
		this.calculadorQuestionario = calculadorQuestionario;
		this.resultadoAvaliacaoRepository = resultadoAvaliacaoRepository;
	}
	
	@Transactional
	public void publicar(Avaliacao avaliacao) {
		// TODO: avaliacao deve ir para status publicada
		Questionario questionario = avaliacao.getQuestionario();
		List<RespostaQuestionario> respostas = questionario.getRespostas();
		
		for (RespostaQuestionario resposta : respostas) {
			BigDecimal notaFinal = calculadorQuestionario.calculaNota(ConversorQuestionario.toBlocos(resposta.getEstrutura()));

			ResultadoAvaliacao resultado = 
					ResultadoAvaliacao.builder()
					.avaliacao(avaliacao)
					.nomeMunicipio(resposta.getMunicipio())
					.nota(notaFinal)
					.build();
			
			resultadoAvaliacaoRepository.put(resultado);
		}
		
		
		
	}

}
