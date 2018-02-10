package br.gov.cgu.mbt.aplicacao.avaliacao.migrador.util;

import java.util.HashMap;
import java.util.Map;

public class EbtUtil {
	
	public final static int RODADA_1 = 1;
	public final static int RODADA_2 = 2;
	public final static int RODADA_3 = 3;
	
	public final static String BLOCO_REGULAMENTACAO = "Regulamentação";
	public final static String BLOCO_TR_PASSIVA = "Transparência Passiva";
	
	// Perguntas do bloco de regulamentação
	public final static String QUESTAO_site_municipal = "Qual o site municipal?";
	public final static String QUESTAO_site_esic = "Qual o site eSIC?";
	public final static String QUESTAO_regulamento_pagina_prefeitura = "O regulamento foi localizado na página eletrônica?";
	public final static String QUESTAO_regulamentou_lai = "O ente regulamentou a LAI?";
	public final static String QUESTAO_regulamentou_criacao_sic = "Regulamentou a criação do SIC?";
	public final static String QUESTAO_previsao_calassificacao = "Existe a previsão da autoridades que podem classificar a informação quanto ao grau de sigilo?";
	public final static String QUESTAO_previsao_responsabilizacao_servidor = "Previsão de responsabilização do servidor em caso de condutas ilícitas;	";
	public final static String QUESTAO_regulamentou_instancia_recursal = "Regulamentou a existência de pelo menos uma instância recursal?	";
	
	// Perguntas do bloco de transparência passiva
	public final static String QUESTAO_existe_indicacao_precisa_sic = "Existe indicação precisa no site de funcionamento de um SIC fisico, ou seja, com a possibilidade de entrega de um pedido de acesso de forma presencial?";
	public final static String QUESTAO_indicacao_orgao = "Indicação do órgão?";
	public final static String QUESTAO_indicacao_endereco = "Indicação de endereço?";
	public final static String QUESTAO_indicacao_telefone = "Indicação de telefone?";
	public final static String QUESTAO_indicacao_horario = "Indicação dos horários de funcionamento?";
	
	public final static String QUESTAO_alternativa_sic_eletronico = "Há alternativa de enviar pedidos de forma eletrônica ao SIC?";
	
	public final static String QUESTAO_nao_exige_identificacao = "Para fazer a solicitação, são exigidos dados de  identificação do requerente que dificultem ou impossibilitem o acesso à informação?";
	public final static String QUESTAO_envio_documento_cadastro = "Envio de qualquer documento para cadastro (PF e PJ)";
	public final static String QUESTAO_assinatura_reconhecida = "Assinatura reconhecida";
	public final static String QUESTAO_declaracao_responsabilidade = "Declaração de responsabilidade";
	public final static String QUESTAO_maioridade = "Maioridade";
	public final static String QUESTAO_outras_exigencias = "Outras exigências";
	
	public final static String QUESTAO_possibilidade_acompanhamento = "Apresenta possibilidade de acompanhamento posterior da solicitação?";
	
	public final static String QUESTAO_resposta_no_prazo = "Cumpre os prazos para resposta das solicitações?";
	public final static String QUESTAO_resposta_no_prazo_pergunta_1 = "Resposta no prazo da 1ª pergunta: Questionamento na área de Saúde";
	public final static String QUESTAO_resposta_no_prazo_pergunta_2 = "Resposta no prazo da 2ª pergunta: Questionamento na área de Educação";
	public final static String QUESTAO_resposta_no_prazo_pergunta_3 = "Resposta no prazo da 3ª pergunta: Questionamento na área de Assistência Social";
	public final static String QUESTAO_resposta_no_prazo_pergunta_4 = "Resposta no prazo da 4ª pergunta: Norma de regulamentação da LAI.";
	
	public final static String QUESTAO_respondeu_pergunta = "Respondeu ao que se perguntou, atendendo ao pedido de informação? ";
	public final static String QUESTAO_respondeu_pergunta_1 = "Resposta em conformidade da 1ª pergunta: Questionamento na área de  Saúde";
	public final static String QUESTAO_respondeu_pergunta_2 = "Resposta em conformidade da 2ª pergunta: Questionamento na área de  Educação";
	public final static String QUESTAO_respondeu_pergunta_3 = "Resposta em conformidade da 3ª pergunta: Questionamento na área de Assitência Social";
	public final static String QUESTAO_respondeu_pergunta_4 = "Resposta em conformidade da 4ª pergunta: Norma de regulamentação da LAI.";
	
	public final static String OPCAO_SIM = "SIM";
	public final static String OPCAO_NAO = "NÃO";
	
	public final static Map<String, QuestionarioEbtHeader> MAPEAMENTO_PERGUNTA_RESPOSTA = new HashMap<String, QuestionarioEbtHeader>();
	
	static {
		// Bloco de regulamentação
		MAPEAMENTO_PERGUNTA_RESPOSTA.put(QUESTAO_site_municipal, QuestionarioEbtHeader.site_municipal);
		MAPEAMENTO_PERGUNTA_RESPOSTA.put(QUESTAO_site_esic, QuestionarioEbtHeader.site_esic);
		MAPEAMENTO_PERGUNTA_RESPOSTA.put(QUESTAO_regulamento_pagina_prefeitura, QuestionarioEbtHeader.regulamento_pagina_prefeitura);
		MAPEAMENTO_PERGUNTA_RESPOSTA.put(QUESTAO_regulamentou_lai, QuestionarioEbtHeader.regulamentou_lai);
		MAPEAMENTO_PERGUNTA_RESPOSTA.put(QUESTAO_regulamentou_criacao_sic, QuestionarioEbtHeader.regulamentou_criacao_sic);
		MAPEAMENTO_PERGUNTA_RESPOSTA.put(QUESTAO_previsao_calassificacao, QuestionarioEbtHeader.previsao_calassificacao);
		MAPEAMENTO_PERGUNTA_RESPOSTA.put(QUESTAO_previsao_responsabilizacao_servidor, QuestionarioEbtHeader.previsao_responsabilizacao_servidor);
		MAPEAMENTO_PERGUNTA_RESPOSTA.put(QUESTAO_regulamentou_instancia_recursal, QuestionarioEbtHeader.regulamentou_instancia_recursal);
		
		// Bloco de transparência passiva
		MAPEAMENTO_PERGUNTA_RESPOSTA.put(QUESTAO_alternativa_sic_eletronico, QuestionarioEbtHeader.alternativa_sic_eletronico);
		MAPEAMENTO_PERGUNTA_RESPOSTA.put(QUESTAO_indicacao_orgao, QuestionarioEbtHeader.indicacao_orgao);
		MAPEAMENTO_PERGUNTA_RESPOSTA.put(QUESTAO_indicacao_endereco, QuestionarioEbtHeader.indicacao_endereco);
		MAPEAMENTO_PERGUNTA_RESPOSTA.put(QUESTAO_indicacao_telefone, QuestionarioEbtHeader.indicacao_telefone);
		MAPEAMENTO_PERGUNTA_RESPOSTA.put(QUESTAO_indicacao_horario, QuestionarioEbtHeader.indicacao_horario);
		MAPEAMENTO_PERGUNTA_RESPOSTA.put(QUESTAO_envio_documento_cadastro, QuestionarioEbtHeader.envio_documento_cadastro);
		MAPEAMENTO_PERGUNTA_RESPOSTA.put(QUESTAO_assinatura_reconhecida, QuestionarioEbtHeader.assinatura_reconhecida);
		MAPEAMENTO_PERGUNTA_RESPOSTA.put(QUESTAO_declaracao_responsabilidade, QuestionarioEbtHeader.declaracao_responsabilidade);
		MAPEAMENTO_PERGUNTA_RESPOSTA.put(QUESTAO_maioridade, QuestionarioEbtHeader.maioridade);
		MAPEAMENTO_PERGUNTA_RESPOSTA.put(QUESTAO_outras_exigencias, QuestionarioEbtHeader.outras_exigencias);
		MAPEAMENTO_PERGUNTA_RESPOSTA.put(QUESTAO_possibilidade_acompanhamento, QuestionarioEbtHeader.possibilidade_acompanhamento);
		MAPEAMENTO_PERGUNTA_RESPOSTA.put(QUESTAO_resposta_no_prazo_pergunta_1, QuestionarioEbtHeader.resposta_no_prazo_pergunta_1);
		MAPEAMENTO_PERGUNTA_RESPOSTA.put(QUESTAO_resposta_no_prazo_pergunta_2, QuestionarioEbtHeader.resposta_no_prazo_pergunta_2);
		MAPEAMENTO_PERGUNTA_RESPOSTA.put(QUESTAO_resposta_no_prazo_pergunta_3, QuestionarioEbtHeader.resposta_no_prazo_pergunta_3);
		MAPEAMENTO_PERGUNTA_RESPOSTA.put(QUESTAO_resposta_no_prazo_pergunta_4, QuestionarioEbtHeader.resposta_no_prazo_pergunta_4);
		MAPEAMENTO_PERGUNTA_RESPOSTA.put(QUESTAO_respondeu_pergunta_1, QuestionarioEbtHeader.respondeu_pergunta_1);
		MAPEAMENTO_PERGUNTA_RESPOSTA.put(QUESTAO_respondeu_pergunta_2, QuestionarioEbtHeader.respondeu_pergunta_2);
		MAPEAMENTO_PERGUNTA_RESPOSTA.put(QUESTAO_respondeu_pergunta_3, QuestionarioEbtHeader.respondeu_pergunta_3);
		MAPEAMENTO_PERGUNTA_RESPOSTA.put(QUESTAO_respondeu_pergunta_4, QuestionarioEbtHeader.respondeu_pergunta_4);
	}

}
