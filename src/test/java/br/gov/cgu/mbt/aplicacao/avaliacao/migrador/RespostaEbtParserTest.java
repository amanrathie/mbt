package br.gov.cgu.mbt.aplicacao.avaliacao.migrador;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import br.gov.cgu.mbt.aplicacao.avaliacao.migrador.util.EbtUtil;

public class RespostaEbtParserTest {
	
	
	
	@Test
	public void parser_ebt_rodada_1() throws Exception {
		RespostaEbtParser parser = new RespostaEbtParser("/ebt/ebt_respostas.csv");
		
		assertThat(parser.parse(EbtUtil.RODADA_1))
			.isNotEmpty()
			.hasSize(519);
	}
	
	@Test
	public void parser_ebt_rodada_2() throws Exception {
		RespostaEbtParser parser = new RespostaEbtParser("/ebt/ebt_respostas.csv");
		
		assertThat(parser.parse(EbtUtil.RODADA_2))
			.isNotEmpty()
			.hasSize(1614);
	}
	
	@Test
	public void parser_ebt_rodada_3() throws Exception {
		RespostaEbtParser parser = new RespostaEbtParser("/ebt/ebt_respostas.csv");
		
		assertThat(parser.parse(EbtUtil.RODADA_3))
			.isNotEmpty()
			.hasSize(2355);
		
	}

}
