package br.gov.cgu.mbt.aplicacao.avaliacao.migrador.builder;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import br.gov.cgu.mbt.negocio.avaliacao.bloco.Bloco;

/**
 * Constrói o questionário da EBT a ser migrado (1,2,3)
 */
public class BlocoEbtBuilder {
	
	public final static String BLOCO_REGULAMENTACAO = "Regulamentação";
	public final static String BLOCO_TR_PASSIVA = "Transparência Passiva";
	

	public List<Bloco> build() {
		Bloco bloco1 = Bloco.builder()
				.nome(BLOCO_REGULAMENTACAO)
				.peso(new BigDecimal(25))
				.ordem(1)
				.build();
		
		Bloco bloco2 = Bloco.builder()
				.nome(BLOCO_TR_PASSIVA)
				.peso(new BigDecimal(75))
				.ordem(2)
				.build();
		
		return Arrays.asList(bloco1, bloco2);
	}
}
