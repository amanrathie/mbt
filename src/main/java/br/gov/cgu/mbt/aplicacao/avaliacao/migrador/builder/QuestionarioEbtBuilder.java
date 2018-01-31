package br.gov.cgu.mbt.aplicacao.avaliacao.migrador.builder;

import br.gov.cgu.mbt.negocio.avaliacao.questionario.Questionario;

/**
 * Constrói o questionário da EBT a ser migrado (1,2,3)
 */
public class QuestionarioEbtBuilder {

	public Questionario build() {
		Questionario questionario = 
				Questionario.builder().build();
		
		return questionario;
	}
}
