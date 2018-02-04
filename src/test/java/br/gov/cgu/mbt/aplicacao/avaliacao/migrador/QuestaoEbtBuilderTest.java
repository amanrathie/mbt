package br.gov.cgu.mbt.aplicacao.avaliacao.migrador;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import br.gov.cgu.mbt.aplicacao.avaliacao.migrador.builder.BlocoEbtBuilder;
import br.gov.cgu.mbt.aplicacao.avaliacao.migrador.builder.QuestaoEbtBuilder;
import br.gov.cgu.mbt.negocio.avaliacao.bloco.Bloco;
import br.gov.cgu.mbt.negocio.avaliacao.questao.Questao;

public class QuestaoEbtBuilderTest {
	
	private QuestaoEbtBuilder questaoEbtBuildr = new QuestaoEbtBuilder();
	
	@Test
	public void questoes_regulamentacao_construido_corretamente() throws Exception {
		Bloco bloco = Bloco.builder()
				.nome(BlocoEbtBuilder.BLOCO_REGULAMENTACAO)
				.build();
		
		assertThat(questaoEbtBuildr.build(bloco))
			.isNotEmpty()
			.hasSize(6)
			.hasOnlyElementsOfType(Questao.class);
	}
	
	@Test
	public void questoes_tr_passiva_construido_corretamente() throws Exception {
		Bloco bloco = Bloco.builder()
				.nome(BlocoEbtBuilder.BLOCO_TR_PASSIVA)
				.build();
		
		assertThat(questaoEbtBuildr.build(bloco))
			.isNotEmpty()
			.hasSize(6)
			.hasOnlyElementsOfType(Questao.class);
	}
}
