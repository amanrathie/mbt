package br.gov.cgu.mbt.negocio.avaliacao.questionario.json;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Singular;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Bloco {
	
	// TODO: gerar automaticamente
	private Integer id;
	
	@Singular("questao")
	private List<Questao> questoes;
	
	private String nome;

	private String descricao;
	
	private BigDecimal peso;
	
	private boolean obrigatorio;
	
	private Integer ordem;
	
}
