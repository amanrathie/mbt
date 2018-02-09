package br.gov.cgu.mbt.aplicacao.avaliacao.migrador.util;

import java.util.HashMap;
import java.util.Map;

public class EbtUtil {
	
	public final static int RODADA_1 = 1;
	public final static int RODADA_2 = 2;
	public final static int RODADA_3 = 3;
	
	public final static String BLOCO_REGULAMENTACAO = "Regulamentação";
	public final static String BLOCO_TR_PASSIVA = "Transparência Passiva";
	
	public final static String QUESTAO_regulamentou_laip = "O regulamento foi localizado na página eletrônica?";
	
	public final static String OPCAO_SIM = "SIM";
	public final static String OPCAO_NAO = "NÃO";
	
	public final static Map<String, QuestionarioEbtHeader> MAPEAMENTO_PERGUNTA_RESPOSTA = new HashMap<String, QuestionarioEbtHeader>();
	
	static {
		MAPEAMENTO_PERGUNTA_RESPOSTA.put(QUESTAO_regulamentou_laip, QuestionarioEbtHeader.regulamentou_lai);
	}

}
