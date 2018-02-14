package br.gov.cgu.mbt.aplicacao.avaliacao.migrador;

import java.math.BigDecimal;
import java.util.List;

import br.gov.cgu.mbt.aplicacao.avaliacao.questionario.calculador.CalculadorQuestao;
import br.gov.cgu.mbt.negocio.avaliacao.questionario.json.OpcaoResposta;
import br.gov.cgu.mbt.negocio.avaliacao.questionario.json.Questao;
import br.gov.cgu.mbt.negocio.avaliacao.questionario.json.QuestaoMultiplaEscolha;

public class CalculadorQuestaoMultiplaEscolhaMigrador implements CalculadorQuestao {

	@Override
	public BigDecimal calcula(Questao questao) {
		BigDecimal notaNaQuestao = BigDecimal.ZERO;
		
		QuestaoMultiplaEscolha qme = (QuestaoMultiplaEscolha)questao;
		
		List<OpcaoResposta> opcoesResposta = qme.getOpcoesResposta();
		
		for (OpcaoResposta opcaoResposta : opcoesResposta) {
			if (opcaoResposta.isResposta()) {
				BigDecimal pesoResposta = opcaoResposta.getPeso();
				
				notaNaQuestao = notaNaQuestao.add(pesoResposta);
			}
		}	
		
		return notaNaQuestao;
	}

}
