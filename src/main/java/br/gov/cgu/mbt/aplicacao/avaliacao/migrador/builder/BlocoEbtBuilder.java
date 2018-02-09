package br.gov.cgu.mbt.aplicacao.avaliacao.migrador.builder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.gov.cgu.mbt.aplicacao.avaliacao.migrador.util.EbtUtil;
import br.gov.cgu.mbt.aplicacao.avaliacao.questionario.ConversorQuestionario;
import br.gov.cgu.mbt.negocio.avaliacao.questionario.json.Bloco;
import br.gov.cgu.mbt.negocio.avaliacao.questionario.json.OpcaoResposta;
import br.gov.cgu.mbt.negocio.avaliacao.questionario.json.Questao;
import br.gov.cgu.mbt.negocio.avaliacao.questionario.json.QuestaoMultiplaEscolha;

/**
 * Constrói o questionário da EBT a ser migrado (1,2,3)
 */
public class BlocoEbtBuilder {
	
	// TODO: mudar o builder para ser estatico, seguindo o padrão builder
	public List<Bloco> build() {
		Bloco bloco1 = Bloco.builder()
				.nome(EbtUtil.BLOCO_REGULAMENTACAO)
				.peso(new BigDecimal(25))
				.ordem(1)
				.questoes(buildQuestoesBlocoRegulamentacao())
				.build();
		
		Bloco bloco2 = Bloco.builder()
				.nome(EbtUtil.BLOCO_TR_PASSIVA)
				.peso(new BigDecimal(75))
				.ordem(2)
				.questoes(buildQuestoesBlocoTransparenciaPassiva())
				.build();
		
		List<Bloco> blocos = Arrays.asList(bloco1, bloco2);
		
		return blocos;
	}
	
	// TODO: mover para QuestaoBuilder
	private List<Questao> buildQuestoesBlocoRegulamentacao() {		
		List<Questao> questoes = Arrays.asList(getQuestaoRegulamentoLAIP());
		
		return questoes;
	}
	
	private List<Questao> buildQuestoesBlocoTransparenciaPassiva() {
		return new ArrayList<Questao>();
	}
	
	private QuestaoMultiplaEscolha getQuestaoRegulamentoLAIP() {		
		QuestaoMultiplaEscolha questaoMultiplaEscolha = QuestaoMultiplaEscolha.builder()
				.pergunta(EbtUtil.QUESTAO_regulamentou_laip)
				.peso(new BigDecimal(11.11)) // Obtido dividindo 2,78 por 25
				.ordem(3)
				.selecaoUnica(true)
				.opcoesResposta(getQuestaoMultiplaEscolhaSimNao())
				.build();
		
		return questaoMultiplaEscolha;
	}
	
	private List<OpcaoResposta> getQuestaoMultiplaEscolhaSimNao() {	
		OpcaoResposta opcaoRespostaSim = OpcaoResposta.builder()
				.opcao(EbtUtil.OPCAO_SIM)
				.peso(new BigDecimal(100))
				.build();
		
		OpcaoResposta opcaoRespostaNao = OpcaoResposta.builder()
				.opcao(EbtUtil.OPCAO_NAO)
				.peso(new BigDecimal(0))
				.build();
		
		return Arrays.asList(opcaoRespostaSim, opcaoRespostaNao);
	}
}
