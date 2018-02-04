package br.gov.cgu.mbt.negocio.avaliacao.questao.json;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Singular;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestaoMultiplaEscolha {
	
	private boolean selecaoUnica;
	private boolean respostaComplementar;
	
	@Singular("opcaoResposta")
	private List<OpcaoResposta> opcoesResposta = new ArrayList<OpcaoResposta>();
}
