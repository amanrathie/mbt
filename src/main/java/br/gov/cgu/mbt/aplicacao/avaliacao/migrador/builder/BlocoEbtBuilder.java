package br.gov.cgu.mbt.aplicacao.avaliacao.migrador.builder;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.gov.cgu.mbt.aplicacao.avaliacao.migrador.util.EbtUtil;
import br.gov.cgu.mbt.negocio.avaliacao.questionario.json.Bloco;
import br.gov.cgu.mbt.negocio.avaliacao.questionario.json.OpcaoMultiplaEscolha;
import br.gov.cgu.mbt.negocio.avaliacao.questionario.json.OpcaoResposta;
import br.gov.cgu.mbt.negocio.avaliacao.questionario.json.Questao;
import br.gov.cgu.mbt.negocio.avaliacao.questionario.json.QuestaoDescritiva;
import br.gov.cgu.mbt.negocio.avaliacao.questionario.json.QuestaoMatriz;
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
				.obrigatorio(true)
				.questoes(buildQuestoesBlocoRegulamentacao())
				.build();
		
		Bloco bloco2 = Bloco.builder()
				.nome(EbtUtil.BLOCO_TR_PASSIVA)
				.peso(new BigDecimal(75))
				.ordem(2)
				.obrigatorio(true)
				.questoes(buildQuestoesBlocoTransparenciaPassiva())
				.build();
		
		List<Bloco> blocos = Arrays.asList(bloco1, bloco2);
		
		return blocos;
	}
	
	// TODO: mover para QuestaoBuilder
	private List<Questao> buildQuestoesBlocoRegulamentacao() {
		QuestaoDescritiva qd1 = QuestaoDescritiva.builder()
				.pergunta(EbtUtil.QUESTAO_site_municipal)
				.ordem(1)
				.build();
		
		QuestaoDescritiva qd2 = QuestaoDescritiva.builder()
				.pergunta(EbtUtil.QUESTAO_site_esic)
				.ordem(2)
				.build();
		
		List<Questao> questoes = new ArrayList<Questao>();
		
		questoes.add(qd1);
		questoes.add(qd2);
		questoes.addAll(getQuestoesRegulamentacao());
		
		return questoes;
	}
	
	private List<Questao> buildQuestoesBlocoTransparenciaPassiva() {
		List<Questao> questoes = new ArrayList<Questao>();
		
		questoes.addAll(getQuestoesTransparenciaPassiva());
		
		return questoes;
	}
	
	private List<Questao> getQuestoesRegulamentacao() {
		QuestaoMultiplaEscolha questao3 = QuestaoMultiplaEscolha.builder()
				.pergunta(EbtUtil.QUESTAO_regulamento_pagina_prefeitura)
				.peso(getPorcentagemPeso(100, 900))
				.ordem(3)
				.selecaoUnica(true)
				.opcoesResposta(getOpcoesRespostaSimNao())
				.build();
		
		QuestaoMultiplaEscolha questao4 = QuestaoMultiplaEscolha.builder()
				.pergunta(EbtUtil.QUESTAO_regulamentou_lai)
				.peso(getPorcentagemPeso(150, 900))
				.ordem(4)
				.selecaoUnica(true)
				.opcoesResposta(getOpcoesRespostaSimNao())
				.build();

		QuestaoMultiplaEscolha questao5 = QuestaoMultiplaEscolha.builder()
				.pergunta(EbtUtil.QUESTAO_regulamentou_criacao_sic)
				.peso(getPorcentagemPeso(200, 900))
				.ordem(5)
				.selecaoUnica(true)
				.opcoesResposta(getOpcoesRespostaSimNao())
				.build();
		
		QuestaoMultiplaEscolha questao6 = QuestaoMultiplaEscolha.builder()
				.pergunta(EbtUtil.QUESTAO_previsao_calassificacao)
				.peso(getPorcentagemPeso(150, 900))
				.ordem(6)
				.selecaoUnica(true)
				.opcoesResposta(getOpcoesRespostaSimNao())
				.build();
		
		QuestaoMultiplaEscolha questao7 = QuestaoMultiplaEscolha.builder()
				.pergunta(EbtUtil.QUESTAO_previsao_responsabilizacao_servidor)
				.peso(getPorcentagemPeso(100, 900))
				.ordem(7)
				.selecaoUnica(true)
				.opcoesResposta(getOpcoesRespostaSimNao())
				.build();
		
		QuestaoMultiplaEscolha questao8 = QuestaoMultiplaEscolha.builder()
				.pergunta(EbtUtil.QUESTAO_regulamentou_instancia_recursal)
				.peso(getPorcentagemPeso(200, 900))
				.ordem(8)
				.selecaoUnica(true)
				.opcoesResposta(getOpcoesRespostaSimNao())
				.build();
		
		return Arrays.asList(questao3, questao4, questao5, questao6, questao7, questao8);
	}
	
	private List<Questao> getQuestoesTransparenciaPassiva() {
		QuestaoMatriz questao1 = QuestaoMatriz.builder()
				.pergunta(EbtUtil.QUESTAO_existe_indicacao_precisa_sic)
				.peso(getPorcentagemPeso(500, 2700))
				.ordem(1)
				.opcaoMultiplaEscolha(OpcaoMultiplaEscolha.builder()
						.pergunta(EbtUtil.QUESTAO_indicacao_orgao)
						.opcoesResposta(getOpcoesRespostaSimNao())
						.peso(getPorcentagemPeso(200, 500))
						.ordem(1)
						.build())
				.opcaoMultiplaEscolha(OpcaoMultiplaEscolha.builder()
						.pergunta(EbtUtil.QUESTAO_indicacao_endereco)
						.opcoesResposta(getOpcoesRespostaSimNao())
						.peso(getPorcentagemPeso(100, 500))
						.ordem(2)
						.build())
				.opcaoMultiplaEscolha(OpcaoMultiplaEscolha.builder()
						.pergunta(EbtUtil.QUESTAO_indicacao_telefone)
						.opcoesResposta(getOpcoesRespostaSimNao())
						.peso(getPorcentagemPeso(100, 500))
						.ordem(3)
						.build())
				.opcaoMultiplaEscolha(OpcaoMultiplaEscolha.builder()
						.pergunta(EbtUtil.QUESTAO_indicacao_horario)
						.opcoesResposta(getOpcoesRespostaSimNao())
						.peso(getPorcentagemPeso(100, 500))
						.ordem(4)
						.build())
				.build();
		
		
		QuestaoMultiplaEscolha questao2 = QuestaoMultiplaEscolha.builder()
				.pergunta(EbtUtil.QUESTAO_alternativa_sic_eletronico)
				.peso(getPorcentagemPeso(200, 2700))
				.ordem(2)
				.selecaoUnica(true)
				.opcoesResposta(getOpcoesRespostaSimNao())
				.build();
		
		// TODO: Verificar se os pesos das opções estão corretas
		
		QuestaoMultiplaEscolha questao3 = QuestaoMultiplaEscolha.builder()
				.pergunta(EbtUtil.QUESTAO_alternativa_sic_eletronico)
				.peso(getPorcentagemPeso(300, 2700))
				.ordem(3)
				.selecaoUnica(true)
				.opcoesResposta(getOpcoesRespostaSimNao())
				.build();
		/*QuestaoMatriz questao3 = QuestaoMatriz.builder() 
				.pergunta(EbtUtil.QUESTAO_nao_exige_identificacao)
				.peso(getPorcentagemPeso(300, 2700))
				.ordem(3)
				.opcaoMultiplaEscolha(OpcaoMultiplaEscolha.builder()
						.pergunta(EbtUtil.QUESTAO_envio_documento_cadastro)
						.opcoesResposta(getOpcoesRespostaSimNao())
						.peso(getPorcentagemPeso(60, 300))
						.ordem(1)
						.build())
				.opcaoMultiplaEscolha(OpcaoMultiplaEscolha.builder()
						.pergunta(EbtUtil.QUESTAO_assinatura_reconhecida)
						.opcoesResposta(getOpcoesRespostaSimNao())
						.peso(getPorcentagemPeso(60, 300))
						.ordem(2)
						.build())
				.opcaoMultiplaEscolha(OpcaoMultiplaEscolha.builder()
						.pergunta(EbtUtil.QUESTAO_declaracao_responsabilidade)
						.opcoesResposta(getOpcoesRespostaSimNao())
						.peso(getPorcentagemPeso(60, 300))
						.ordem(3)
						.build())
				.opcaoMultiplaEscolha(OpcaoMultiplaEscolha.builder()
						.pergunta(EbtUtil.QUESTAO_maioridade)
						.opcoesResposta(getOpcoesRespostaSimNao())
						.peso(getPorcentagemPeso(60, 300))
						.ordem(4)
						.build())
				.opcaoMultiplaEscolha(OpcaoMultiplaEscolha.builder()
						.pergunta(EbtUtil.QUESTAO_outras_exigencias)
						.opcoesResposta(getOpcoesRespostaSimNao())
						.peso(getPorcentagemPeso(60, 300))
						.ordem(5)
						.build())
				.build();*/
		
		QuestaoMultiplaEscolha questao4 = QuestaoMultiplaEscolha.builder()
				.pergunta(EbtUtil.QUESTAO_possibilidade_acompanhamento)
				.peso(getPorcentagemPeso(200, 2700))
				.ordem(4)
				.selecaoUnica(true)
				.opcoesResposta(getOpcoesRespostaSimNao())
				.build();
		
		QuestaoMatriz questao5 = QuestaoMatriz.builder()
				.pergunta(EbtUtil.QUESTAO_resposta_no_prazo)
				.peso(getPorcentagemPeso(500, 2700))
				.ordem(5)
				.opcaoMultiplaEscolha(OpcaoMultiplaEscolha.builder()
						.pergunta(EbtUtil.QUESTAO_resposta_no_prazo_pergunta_1)
						.opcoesResposta(getOpcoesRespostaSimNao())
						.peso(getPorcentagemPeso(100, 500))
						.ordem(1)
						.build())
				.opcaoMultiplaEscolha(OpcaoMultiplaEscolha.builder()
						.pergunta(EbtUtil.QUESTAO_resposta_no_prazo_pergunta_2)
						.opcoesResposta(getOpcoesRespostaSimNao())
						.peso(getPorcentagemPeso(100, 500))
						.ordem(2)
						.build())
				.opcaoMultiplaEscolha(OpcaoMultiplaEscolha.builder()
						.pergunta(EbtUtil.QUESTAO_resposta_no_prazo_pergunta_3)
						.opcoesResposta(getOpcoesRespostaSimNao())
						.peso(getPorcentagemPeso(150, 500))
						.ordem(3)
						.build())
				.opcaoMultiplaEscolha(OpcaoMultiplaEscolha.builder()
						.pergunta(EbtUtil.QUESTAO_resposta_no_prazo_pergunta_4)
						.opcoesResposta(getOpcoesRespostaSimNao())
						.peso(getPorcentagemPeso(150, 500))
						.ordem(4)
						.build())
				.build();
		
		QuestaoMatriz questao6 = QuestaoMatriz.builder()
				.pergunta(EbtUtil.QUESTAO_respondeu_pergunta)
				.peso(getPorcentagemPeso(1000, 2700))
				.ordem(1)
				.opcaoMultiplaEscolha(OpcaoMultiplaEscolha.builder()
						.pergunta(EbtUtil.QUESTAO_respondeu_pergunta_1)
						.opcoesResposta(getOpcoesRespostaSimNao())
						.peso(getPorcentagemPeso(200, 1000))
						.ordem(1)
						.build())
				.opcaoMultiplaEscolha(OpcaoMultiplaEscolha.builder()
						.pergunta(EbtUtil.QUESTAO_respondeu_pergunta_2)
						.opcoesResposta(getOpcoesRespostaSimNao())
						.peso(getPorcentagemPeso(200, 1000))
						.ordem(2)
						.build())
				.opcaoMultiplaEscolha(OpcaoMultiplaEscolha.builder()
						.pergunta(EbtUtil.QUESTAO_respondeu_pergunta_3)
						.opcoesResposta(getOpcoesRespostaSimNao())
						.peso(getPorcentagemPeso(300, 1000))
						.ordem(3)
						.build())
				.opcaoMultiplaEscolha(OpcaoMultiplaEscolha.builder()
						.pergunta(EbtUtil.QUESTAO_respondeu_pergunta_4)
						.opcoesResposta(getOpcoesRespostaSimNao())
						.peso(getPorcentagemPeso(300, 1000))
						.ordem(4)
						.build())
				.build();
		
		return Arrays.asList(questao1, questao2, questao3, questao4, questao5, questao6);
	}
	
	private List<OpcaoResposta> getOpcoesRespostaSimNao() {	
		OpcaoResposta opcaoRespostaSim = OpcaoResposta.builder()
				.opcao(EbtUtil.OPCAO_SIM)
				.peso(new BigDecimal(100))
				.build();
		
		OpcaoResposta opcaoRespostaNao = OpcaoResposta.builder()
				.opcao(EbtUtil.OPCAO_NAO)
				.peso(BigDecimal.ZERO)
				.build();
		
		return Arrays.asList(opcaoRespostaSim, opcaoRespostaNao);
	}
	
	// Retorna com precisão de 4 casas decimais
	private BigDecimal getPorcentagemPeso(int valorPergunta, int valorBloco) {
		return new BigDecimal(valorPergunta).divide(new BigDecimal(valorBloco), 6, RoundingMode.HALF_UP).multiply(new BigDecimal(100));
	}

}
