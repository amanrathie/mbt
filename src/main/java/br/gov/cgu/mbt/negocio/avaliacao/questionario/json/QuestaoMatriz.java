package br.gov.cgu.mbt.negocio.avaliacao.questionario.json;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import br.gov.cgu.mbt.negocio.avaliacao.questao.TipoQuestao;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Singular;

@Data
@NoArgsConstructor
public class QuestaoMatriz extends Questao {
	
	private List<OpcaoMultiplaEscolha> opcoesMultiplaEscolha = new ArrayList<OpcaoMultiplaEscolha>();
	
	@Builder
	public QuestaoMatriz(String pergunta, BigDecimal peso, Integer ordem,
			@Singular("opcaoMultiplaEscolha")List<OpcaoMultiplaEscolha> opcoesMultiplaEscolha,
			boolean obrigatoria) {
		super(null, TipoQuestao.MATRIZ, pergunta, peso, ordem, obrigatoria);
		
		this.opcoesMultiplaEscolha = opcoesMultiplaEscolha; 
	}
}
