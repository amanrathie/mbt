package br.gov.cgu.mbt.negocio.avaliacao.questionario.json;

import java.math.BigDecimal;
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
public class OpcaoMultiplaEscolha {
	
	@Singular("opcaoResposta")
	private List<OpcaoResposta> opcoesResposta = new ArrayList<OpcaoResposta>();
	private String pergunta;
	private BigDecimal peso;
	private Integer ordem;
	
}
