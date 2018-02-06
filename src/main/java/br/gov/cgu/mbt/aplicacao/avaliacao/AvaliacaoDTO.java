package br.gov.cgu.mbt.aplicacao.avaliacao;

import br.gov.cgu.persistencia.querybuilder.Filtro;
import lombok.Data;

@Data
public class AvaliacaoDTO extends Filtro {
	
	private String nome;
}
