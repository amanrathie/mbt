package br.gov.cgu.mbt.aplicacao.avaliacao.questionario.calculador;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import br.gov.cgu.mbt.negocio.avaliacao.questionario.json.OpcaoMultiplaEscolha;
import br.gov.cgu.mbt.negocio.avaliacao.questionario.json.OpcaoResposta;
import br.gov.cgu.mbt.negocio.avaliacao.questionario.json.Questao;
import br.gov.cgu.mbt.negocio.avaliacao.questionario.json.QuestaoMatriz;

public class CalculadorQuestaoMatriz implements CalculadorQuestao {

	@Override
	public BigDecimal calcula(Questao questao) {
		BigDecimal valor100 = new BigDecimal(100);
		BigDecimal notaNaQuestao = BigDecimal.ZERO;
		
		QuestaoMatriz qm = (QuestaoMatriz)questao;
		
		List<OpcaoMultiplaEscolha> opcoesMultiplaEscolha = qm.getOpcoesMultiplaEscolha();
		
		for (OpcaoMultiplaEscolha opcaoMultiplaEscolha : opcoesMultiplaEscolha) {
			List<OpcaoResposta> opcoesResposta = opcaoMultiplaEscolha.getOpcoesResposta();
			
			for (OpcaoResposta opcaoResposta : opcoesResposta) {
				if (opcaoResposta.isResposta()) {
					BigDecimal pesoResposta = opcaoResposta.getPeso();
					
					BigDecimal notaNaOpcaoResposta = pesoResposta.multiply(opcaoMultiplaEscolha.getPeso()).divide(valor100, 4, RoundingMode.HALF_UP);
					
					notaNaQuestao = notaNaQuestao.add(notaNaOpcaoResposta);
				}
			}	
		}
		
		return notaNaQuestao;
	}

}
