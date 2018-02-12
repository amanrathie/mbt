
package br.gov.cgu.mbt.aplicacao.avaliacao.questionario.calculador;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.springframework.stereotype.Service;

import br.gov.cgu.mbt.negocio.avaliacao.questao.TipoQuestao;
import br.gov.cgu.mbt.negocio.avaliacao.questionario.json.Bloco;
import br.gov.cgu.mbt.negocio.avaliacao.questionario.json.Questao;

@Service
public class CalculadorQuestionario {
		
	public BigDecimal calculaNota(List<Bloco> blocos) {
		
		BigDecimal valor100 = new BigDecimal(100);
		BigDecimal notaFinal = BigDecimal.ZERO;
		
		for (Bloco bloco : blocos) {
			BigDecimal notaNoBloco = BigDecimal.ZERO;
			BigDecimal pesoBloco = bloco.getPeso();
				
			List<Questao> questoes = bloco.getQuestoes();
			for (Questao questao : questoes) {
				BigDecimal pesoQuestao = questao.getPeso();
				
				if (questao.getTipo().equals(TipoQuestao.MULTIPLA_ESCOLHA)) {
					CalculadorQuestaoMultiplaEscolha calculador = new CalculadorQuestaoMultiplaEscolha();
					BigDecimal notaNaQuestao = calculador.calcula(questao);
					
					notaNoBloco = notaNoBloco.add(notaNaQuestao.multiply(questao.getPeso()).divide(valor100, 4, RoundingMode.HALF_UP));
				} else if (questao.getTipo().equals(TipoQuestao.MATRIZ)) {
					//CalculadorQuestaoMatriz calculador = new CalculadorQuestaoMatriz();
					CalculadorQuestaoMatrizEbtAntigo calculador = new CalculadorQuestaoMatrizEbtAntigo();
					BigDecimal notaNaQuestao = calculador.calcula(questao);
					
					notaNoBloco = notaNoBloco.add(notaNaQuestao.multiply(questao.getPeso()).divide(valor100, 4, RoundingMode.HALF_UP));
				}
			}
			
			notaNoBloco = notaNoBloco.multiply(pesoBloco).divide(valor100);
			notaFinal = notaFinal.add(notaNoBloco);
		}
		
		return notaFinal.divide(new BigDecimal(10), 2, RoundingMode.HALF_UP);
	}
}
