package br.gov.cgu.mbt.negocio.avaliacao.questionario.json;

import java.math.BigDecimal;

import br.gov.cgu.mbt.negocio.avaliacao.questao.TipoQuestao;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class QuestaoDescritiva extends Questao {
	
	private String resposta;
	
	@Builder
	public QuestaoDescritiva(String pergunta, Integer ordem, String resposta, boolean obrigatoria) {
		super(null, TipoQuestao.DESCRITIVA, pergunta, BigDecimal.ZERO, ordem, obrigatoria);
		
		this.resposta = resposta;
	}
}
