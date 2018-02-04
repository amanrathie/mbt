package br.gov.cgu.mbt.aplicacao.avaliacao.migrador;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.gov.cgu.mbt.negocio.avaliacao.questao.Questao;
import br.gov.cgu.mbt.negocio.avaliacao.questao.json.OpcaoResposta;
import br.gov.cgu.mbt.negocio.avaliacao.questao.json.QuestaoMultiplaEscolha;

public class RespostaEbtParserTest {
	
	
	
	@Test
	public void parser_feito_corretamente() throws Exception {
		RespostaEbtParser parser = new RespostaEbtParser("/ebt/ebt_respostas.csv");
		ObjectMapper mapper = new ObjectMapper();
		
		OpcaoResposta opcaoRespostaSim = OpcaoResposta.builder()
				.opcao("SIM")
				.peso(new BigDecimal(100))
				.build();
		
		OpcaoResposta opcaoRespostaNao = OpcaoResposta.builder()
				.opcao("NAO")
				.peso(new BigDecimal(0))
				.build();
		
		QuestaoMultiplaEscolha questaoMultiplaEscolha = QuestaoMultiplaEscolha.builder()
				.selecaoUnica(true)
				.opcaoResposta(opcaoRespostaSim)
				.opcaoResposta(opcaoRespostaNao)
				.build();
		
		Questao questao = new Questao();
		questao.setEstrutura(mapper.writeValueAsString(questaoMultiplaEscolha));
		
		assertThat(parser.parse(1, questao))
			.isNotEmpty();
		
	}

}
