package br.gov.cgu.mbt.aplicacao.avaliacao.migrador.builder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.gov.cgu.mbt.negocio.avaliacao.bloco.Bloco;
import br.gov.cgu.mbt.negocio.avaliacao.questao.Questao;
import br.gov.cgu.mbt.negocio.avaliacao.questao.TipoQuestao;
import br.gov.cgu.mbt.negocio.avaliacao.questao.json.OpcaoResposta;
import br.gov.cgu.mbt.negocio.avaliacao.questao.json.QuestaoMultiplaEscolha;

/**
 * Constrói o questionário da EBT a ser migrado (1,2,3)
 */
public class QuestaoEbtBuilder {

	public List<Questao> build(Bloco bloco) throws JsonProcessingException {
		List<Questao> questoes = new ArrayList<Questao>();
		ObjectMapper mapper = new ObjectMapper();
		
		if (bloco.getNome().equals(BlocoEbtBuilder.BLOCO_REGULAMENTACAO)) {	
			Questao questao3 = Questao.builder()
					.pergunta("O regulamento foi localizado na página eletrônica?")
					.tipo(TipoQuestao.MULTIPLA_ESCOLHA)
					.peso(new BigDecimal(11.11)) // Obtido dividindo 2,78 por 25
					.estrutura(mapper.writeValueAsString(getQuestaoMultiplaEscolhaSimNao()))
					.ordem(3)
					.build();
			
			Questao questao5 = Questao.builder()
					.pergunta("Regulamentou a criação do SIC?")
					.tipo(TipoQuestao.MULTIPLA_ESCOLHA)
					.peso(new BigDecimal(22.22))
					.estrutura(mapper.writeValueAsString(getQuestaoMultiplaEscolhaSimNao()))
					.ordem(5)
					.build();
			
			questoes = Arrays.asList(questao3, questao5);
		}
		
		return questoes;
	}
	
	private QuestaoMultiplaEscolha getQuestaoMultiplaEscolhaSimNao() {	
		OpcaoResposta opcaoRespostaSim = OpcaoResposta.builder()
				.opcao("SIM")
				.peso(new BigDecimal(100))
				.build();
		
		OpcaoResposta opcaoRespostaNao = OpcaoResposta.builder()
				.opcao("NAO")
				.peso(new BigDecimal(0))
				.build();
		
		QuestaoMultiplaEscolha questaoMultiplaEscolha = QuestaoMultiplaEscolha.builder()
				.selecaoUnica(true)
				.opcaoResposta(opcaoRespostaSim)
				.opcaoResposta(opcaoRespostaNao)
				.build();
		
		return questaoMultiplaEscolha;
	}
}
