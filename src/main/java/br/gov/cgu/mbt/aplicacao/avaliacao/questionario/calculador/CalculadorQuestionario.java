
package br.gov.cgu.mbt.aplicacao.avaliacao.questionario.calculador;

import java.math.BigDecimal;
import java.util.List;

import br.gov.cgu.mbt.negocio.avaliacao.questionario.json.Bloco;

public interface CalculadorQuestionario {
		
	public BigDecimal calculaNota(List<Bloco> blocos);
}
