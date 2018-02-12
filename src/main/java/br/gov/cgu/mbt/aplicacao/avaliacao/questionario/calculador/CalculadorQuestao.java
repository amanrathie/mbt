package br.gov.cgu.mbt.aplicacao.avaliacao.questionario.calculador;

import java.math.BigDecimal;

import br.gov.cgu.mbt.negocio.avaliacao.questionario.json.Questao;

public interface CalculadorQuestao {
	
	public BigDecimal calcula(Questao questao);

}
