package br.gov.cgu.mbt.aplicacao.avaliacao.migrador.util;

/**
 * Contém os headers do arquivo CSV de migração das avaliações EBT
 */
public enum QuestionarioEbtHeader {
	rodada, posicao_ebt, nota, dt_inicio_avaliacao, dt_fim_avaliacao, 
	uf, nome_uf, municipio, cod_ibge, populacao, site_municipal, site_esic, 
	regulamento_pagina_prefeitura, regulamento_pagina_prefeiturap, regulamentou_lai, 
	regulamentou_laip, tipo_regulamentacao_lai, normativo_lai, dt_normativo_lai, 
	regulamentou_criacao_sic, regulamentou_criacao_sicp, previsao_calassificacao, 
	previsao_calassificacaop, previsao_responsabilizacao_servidor, previsao_responsabilizacao_servidorp, 
	regulamentou_instancia_recursal, regulamentou_instancia_recursalp, pontos_regulamentacao, 
	indicacao_orgao, indicacao_orgaop, indicacao_endereco, indicacao_enderecop, indicacao_telefone, 
	indicacao_telefonep, indicacao_horario, indicacao_horariop, alternativa_sic_eletronico, 
	alternativa_sic_eletronicop, envio_documento_cadastro, assinatura_reconhecida, declaracao_responsabilidade, 
	maioridade, outras_exigencias, nao_exige_identificacaop, possibilidade_acompanhamento, 
	possibilidade_acompanhamentop, resposta_no_prazo_pergunta_1, resposta_no_prazo_pergunta_2, 
	resposta_no_prazo_pergunta_3, resposta_no_prazo_pergunta_4, pontos_prazo_perguntas, respondeu_pergunta_1, 
	respondeu_pergunta_2, respondeu_pergunta_3, respondeu_pergunta_4, pontos_resposta_perguntas, 
	pontos_transp_passiva, total_pontos, nota_1
}
