package br.gov.cgu.mbt.aplicacao.avaliacao.questionario;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import br.gov.cgu.mbt.negocio.avaliacao.questionario.json.Bloco;
import br.gov.cgu.mbt.negocio.avaliacao.questionario.json.Questao;

@Service
public class CalculadorQuestionario {
		
	public BigDecimal calculaNota(List<Bloco> blocos) {

		for (Bloco bloco : blocos) {
			BigDecimal pesoBloco = bloco.getPeso();
			
			List<Questao> questoes = bloco.getQuestoes();
			for (Questao questao : questoes) {
				BigDecimal pesoQuestao = questao.getPeso();
			}
		}
			
		return new BigDecimal(10);
	}
	
	private void calculaNotaQuestao(Questao questao) {
		
	}

}
