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
public class QuestaoMultiplaEscolha extends Questao {
	
	private boolean selecaoUnica;
	private boolean respostaComplementar;
	private List<OpcaoResposta> opcoesResposta = new ArrayList<OpcaoResposta>();
	
	@Builder
	public QuestaoMultiplaEscolha(String pergunta, BigDecimal peso, Integer ordem,
			boolean selecaoUnica, boolean respostaComplementar, @Singular("opcaoResposta")List<OpcaoResposta> opcoesResposta) {
		super(null, TipoQuestao.MULTIPLA_ESCOLHA, pergunta, peso, ordem);
		
		this.selecaoUnica = selecaoUnica;
		this.respostaComplementar = respostaComplementar;
		this.opcoesResposta = opcoesResposta;
	}
}
