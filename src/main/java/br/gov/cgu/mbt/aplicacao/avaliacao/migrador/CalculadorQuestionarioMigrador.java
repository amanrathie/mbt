
package br.gov.cgu.mbt.aplicacao.avaliacao.migrador;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import br.gov.cgu.mbt.aplicacao.avaliacao.questionario.calculador.CalculadorQuestaoMultiplaEscolha;
import br.gov.cgu.mbt.aplicacao.avaliacao.questionario.calculador.CalculadorQuestionario;
import br.gov.cgu.mbt.negocio.avaliacao.questao.TipoQuestao;
import br.gov.cgu.mbt.negocio.avaliacao.questionario.json.Bloco;
import br.gov.cgu.mbt.negocio.avaliacao.questionario.json.Questao;

@Service
@Qualifier("calculadorQuestionarioMigrador")
public class CalculadorQuestionarioMigrador implements CalculadorQuestionario {
		
	public BigDecimal calculaNota(List<Bloco> blocos) {
		
		BigDecimal valor100 = new BigDecimal(100);
		BigDecimal notaFinal = BigDecimal.ZERO;
		
		for (Bloco bloco : blocos) {
			BigDecimal notaNoBloco = BigDecimal.ZERO;
			BigDecimal pesoBloco = bloco.getPeso();
				
			List<Questao> questoes = bloco.getQuestoes();
			for (Questao questao : questoes) {
				if (questao.getTipo().equals(TipoQuestao.MULTIPLA_ESCOLHA)) {
					CalculadorQuestaoMultiplaEscolha calculador = new CalculadorQuestaoMultiplaEscolha();
					BigDecimal notaNaQuestao = calculador.calcula(questao);
					
					notaNoBloco = notaNoBloco.add(notaNaQuestao.multiply(questao.getPeso()).divide(valor100, 4, RoundingMode.HALF_UP));
				} else if (questao.getTipo().equals(TipoQuestao.MATRIZ)) {
					CalculadorQuestaoMatrizMigrador calculador = new CalculadorQuestaoMatrizMigrador();
					BigDecimal notaNaQuestao = calculador.calcula(questao);
					
					notaNoBloco = notaNoBloco.add(notaNaQuestao.multiply(questao.getPeso()).divide(valor100, 4, RoundingMode.HALF_UP));
				}
			}
			
			notaNoBloco = notaNoBloco.multiply(pesoBloco).divide(valor100);
			notaFinal = notaFinal.add(notaNoBloco);
		}
		
		return notaFinal.divide(new BigDecimal(10), 4, RoundingMode.HALF_UP);
	}
}
