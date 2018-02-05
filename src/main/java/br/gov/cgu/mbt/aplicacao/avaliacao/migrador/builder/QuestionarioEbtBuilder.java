package br.gov.cgu.mbt.aplicacao.avaliacao.migrador.builder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.gov.cgu.mbt.negocio.avaliacao.questao.TipoQuestao;
import br.gov.cgu.mbt.negocio.avaliacao.questionario.json.Bloco;
import br.gov.cgu.mbt.negocio.avaliacao.questionario.json.OpcaoResposta;
import br.gov.cgu.mbt.negocio.avaliacao.questionario.json.Questao;
import br.gov.cgu.mbt.negocio.avaliacao.questionario.json.QuestaoMultiplaEscolha;

/**
 * Constrói o questionário da EBT a ser migrado (1,2,3)
 */
public class QuestionarioEbtBuilder {
	
	public final static String BLOCO_REGULAMENTACAO = "Regulamentação";
	public final static String BLOCO_TR_PASSIVA = "Transparência Passiva";
	

	// TODO: mudar o builder para ser estatico, seguindo o padrão builder
	public String build() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		
		Bloco bloco1 = Bloco.builder()
				.nome(BLOCO_REGULAMENTACAO)
				.peso(new BigDecimal(25))
				.ordem(1)
				.questoes(buildQuestoesBlocoRegulamentacao())
				.build();
		
		Bloco bloco2 = Bloco.builder()
				.nome(BLOCO_TR_PASSIVA)
				.peso(new BigDecimal(75))
				.ordem(2)
				.questoes(buildQuestoesBlocoTransparenciaPassiva())
				.build();
		
		List<Bloco> blocos = Arrays.asList(bloco1, bloco2);
		
		return mapper.writeValueAsString(blocos);
	}
	
	// TODO: mover para QuestaoBuilder
	private List<Questao> buildQuestoesBlocoRegulamentacao() {
		Questao questao3 = Questao.builder()
				.pergunta("O regulamento foi localizado na página eletrônica?")
				.tipo(TipoQuestao.MULTIPLA_ESCOLHA)
				.peso(new BigDecimal(11.11)) // Obtido dividindo 2,78 por 25
				//.estrutura(mapper.writeValueAsString(getQuestaoMultiplaEscolhaSimNao()))
				.ordem(3)
				.build();
		
		Questao questao5 = Questao.builder()
				.pergunta("Regulamentou a criação do SIC?")
				.tipo(TipoQuestao.MULTIPLA_ESCOLHA)
				.peso(new BigDecimal(22.22))
				//.estrutura(mapper.writeValueAsString(getQuestaoMultiplaEscolhaSimNao()))
				.ordem(5)
				.build();
		
		List<Questao> questoes = Arrays.asList(questao3, questao5);
		
		return questoes;
	}
	
	private List<Questao> buildQuestoesBlocoTransparenciaPassiva() {
		return new ArrayList<Questao>();
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
