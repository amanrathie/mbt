package br.gov.cgu.mbt.aplicacao.avaliacao.migrador.builder;

import br.gov.cgu.mbt.negocio.avaliacao.Avaliacao;
import br.gov.cgu.mbt.negocio.avaliacao.questionario.Questionario;

/**
 * Constrói o questionário da EBT a ser migrado (1,2,3)
 */
public class QuestionarioEbtBuilder {

	public Questionario build(Avaliacao avaliacao) {
		Questionario questionario = 
				Questionario.builder()
					.avaliacao(avaliacao)
					.build();
		
		return questionario;
	}
}
