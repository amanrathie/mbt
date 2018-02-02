package br.gov.cgu.mbt.aplicacao.avaliacao.migrador.builder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.gov.cgu.mbt.negocio.avaliacao.bloco.Bloco;
import br.gov.cgu.mbt.negocio.avaliacao.questao.Questao;
import br.gov.cgu.mbt.negocio.avaliacao.questao.TipoQuestao;

/**
 * Constrói o questionário da EBT a ser migrado (1,2,3)
 */
public class QuestaoEbtBuilder {

	public List<Questao> build(Bloco bloco) {
		List<Questao> questoes = new ArrayList<Questao>();
		
		if (bloco.getNome().equals(BlocoEbtBuilder.BLOCO_REGULAMENTACAO)) {
			Questao questao1 = Questao.builder()
					.pergunta("O regulamento foi localizado na página eletrônica?")
					.tipo(TipoQuestao.MULTIPLA_ESCOLHA)
					.peso(new BigDecimal(11.11)) // Obtido dividindo 2,78 por 25 
					.build();
			
			Questao questao3 = Questao.builder()
					.pergunta("Regulamentou a criação do SIC?")
					.tipo(TipoQuestao.MULTIPLA_ESCOLHA)
					.peso(new BigDecimal(22.22))
					.build();
			
			questoes = Arrays.asList(questao1, questao3);
		}
		
		return questoes;
	}
}
