package br.gov.cgu.mbt.aplicacao.avaliacao.questionario;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import br.gov.cgu.mbt.negocio.avaliacao.questao.TipoQuestao;
import br.gov.cgu.mbt.negocio.avaliacao.questionario.json.Bloco;
import br.gov.cgu.mbt.negocio.avaliacao.questionario.json.OpcaoResposta;
import br.gov.cgu.mbt.negocio.avaliacao.questionario.json.Questao;
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
							
							BigDecimal notaNaQuestao = pesoResposta.multiply(pesoQuestao).divide(valor100);
							notaNoBloco = notaNoBloco.add(notaNaQuestao);
						}
					}	
				}
			}
			
			notaNoBloco = notaNoBloco.multiply(pesoBloco).divide(valor100);
			notaFinal = notaFinal.add(notaNoBloco);
		}
		
		return notaFinal;
	}
	}
