package br.gov.cgu.mbt.aplicacao.avaliacao.migrador.util;

import java.util.HashMap;
import java.util.Map;

import br.gov.cgu.mbt.negocio.avaliacao.questao.Questao;

/**
 * Classe criada para mapear as questões da EBT com os indices do arquivo de resposta
 */
public class QuestaoQuestionarioEbtHeaderMapper {
	
	public static Map<Questao, QuestionarioEbtHeader> mapper = new HashMap<Questao, QuestionarioEbtHeader>();

}
