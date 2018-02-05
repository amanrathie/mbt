package br.gov.cgu.mbt.negocio.avaliacao.questionario.json;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OpcaoResposta {
	private String opcao;
	private BigDecimal peso;
}
