package br.gov.cgu.mbt.aplicacao.avaliacao.questionario;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.springframework.stereotype.Service;

import br.gov.cgu.mbt.negocio.avaliacao.questao.TipoQuestao;
import br.gov.cgu.mbt.negocio.avaliacao.questionario.json.Bloco;
import br.gov.cgu.mbt.negocio.avaliacao.questionario.json.OpcaoMultiplaEscolha;
import br.gov.cgu.mbt.negocio.avaliacao.questionario.json.OpcaoResposta;
import br.gov.cgu.mbt.negocio.avaliacao.questionario.json.Questao;
import br.gov.cgu.mbt.negocio.avaliacao.questionario.json.QuestaoMatriz;
import br.gov.cgu.mbt.negocio.avaliacao.questionario.json.QuestaoMultiplaEscolha;

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
					QuestaoMultiplaEscolha qme = (QuestaoMultiplaEscolha)questao;
					
					List<OpcaoResposta> opcoesResposta = qme.getOpcoesResposta();
					
					for (OpcaoResposta opcaoResposta : opcoesResposta) {
						if (opcaoResposta.isResposta()) {
							BigDecimal pesoResposta = opcaoResposta.getPeso();
							
							BigDecimal notaNaQuestao = pesoResposta.multiply(pesoQuestao).divide(valor100, 4, RoundingMode.HALF_UP);
							notaNoBloco = notaNoBloco.add(notaNaQuestao);
						}
					}	
				} else if (questao.getTipo().equals(TipoQuestao.MATRIZ)) {
					QuestaoMatriz qm = (QuestaoMatriz)questao;
					
					List<OpcaoMultiplaEscolha> opcoesMultiplaEscolha = qm.getOpcoesMultiplaEscolha();
					
					for (OpcaoMultiplaEscolha opcaoMultiplaEscolha : opcoesMultiplaEscolha) {
						List<OpcaoResposta> opcoesResposta = opcaoMultiplaEscolha.getOpcoesResposta();
						
						for (OpcaoResposta opcaoResposta : opcoesResposta) {
							if (opcaoResposta.isResposta()) {
								BigDecimal pesoResposta = opcaoResposta.getPeso();
								
								BigDecimal notaNaOpcao = pesoResposta.multiply(opcaoMultiplaEscolha.getPeso()).divide(valor100, 4, RoundingMode.HALF_UP);
								
								notaNoBloco = notaNoBloco.add(notaNaOpcao.multiply(pesoQuestao).divide(valor100, 4, RoundingMode.HALF_UP));
							}
						}	
					}
				}
			}
			
			notaNoBloco = notaNoBloco.multiply(pesoBloco).divide(valor100);
			notaFinal = notaFinal.add(notaNoBloco);
		}
		
		return notaFinal.divide(new BigDecimal(10), 2, RoundingMode.HALF_UP);
	}
}
