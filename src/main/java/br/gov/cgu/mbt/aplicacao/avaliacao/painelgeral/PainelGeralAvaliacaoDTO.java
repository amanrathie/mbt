package br.gov.cgu.mbt.aplicacao.avaliacao.painelgeral;

import br.gov.cgu.persistencia.querybuilder.Filtro;
import lombok.Data;

@Data
public class PainelGeralAvaliacaoDTO extends Filtro {
	
	private String nome;
}
