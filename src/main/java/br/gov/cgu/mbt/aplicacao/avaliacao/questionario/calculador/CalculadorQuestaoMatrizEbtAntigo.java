package br.gov.cgu.mbt.aplicacao.avaliacao.questionario.calculador;

import java.math.BigDecimal;
import java.util.List;

import br.gov.cgu.mbt.aplicacao.avaliacao.migrador.util.EbtUtil;
import br.gov.cgu.mbt.negocio.avaliacao.questionario.json.OpcaoMultiplaEscolha;
import br.gov.cgu.mbt.negocio.avaliacao.questionario.json.OpcaoResposta;
import br.gov.cgu.mbt.negocio.avaliacao.questionario.json.Questao;
import br.gov.cgu.mbt.negocio.avaliacao.questionario.json.QuestaoMatriz;

public class CalculadorQuestaoMatrizEbtAntigo implements CalculadorQuestao {

	@Override
	public BigDecimal calcula(Questao questao) {
		BigDecimal notaNaQuestao = BigDecimal.ZERO;
		
		QuestaoMatriz qm = (QuestaoMatriz)questao;
		
		List<OpcaoMultiplaEscolha> opcoesMultiplaEscolha = qm.getOpcoesMultiplaEscolha();
		
		if (qm.getPergunta().equals(EbtUtil.QUESTAO_existe_indicacao_precisa_sic)) {
			CalculadorQuestaoMatriz calculador = new CalculadorQuestaoMatriz();
			notaNaQuestao = calculador.calcula(qm);
		} else { // Tratamento especial de certas questões apenas para a migração. O problema é que não existe padronização de pesos em 2 questões do tipo matriz
			int countSim = 0;
			for (OpcaoMultiplaEscolha opcaoMultiplaEscolha : opcoesMultiplaEscolha) {
				List<OpcaoResposta> opcoesResposta = opcaoMultiplaEscolha.getOpcoesResposta();
				
				for (OpcaoResposta opcaoResposta : opcoesResposta) {
					if (opcaoResposta.isResposta() && opcaoResposta.getOpcao().equalsIgnoreCase(EbtUtil.OPCAO_SIM)) {
						countSim++;
						
						if (countSim >= 3) {
							notaNaQuestao = notaNaQuestao.add(new BigDecimal(30));
						} else {
							notaNaQuestao = notaNaQuestao.add(new BigDecimal(20));
						}
						
					}
				}	
			}
		}
		
		return notaNaQuestao;
	}

}
