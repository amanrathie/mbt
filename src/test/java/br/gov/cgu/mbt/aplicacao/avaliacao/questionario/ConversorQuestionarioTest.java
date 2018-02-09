package br.gov.cgu.mbt.aplicacao.avaliacao.questionario;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;

import br.gov.cgu.mbt.aplicacao.avaliacao.migrador.builder.BlocoEbtBuilder;
import br.gov.cgu.mbt.negocio.avaliacao.questionario.json.Bloco;

public class ConversorQuestionarioTest {
	
	@Test
	public void converte_json_para_blocos() {
		String jsonQuestionario = "[{\"id\":null,\"questoes\":[{\"type\":\"questaoMultiplaEscolha\",\"id\":null,\"tipo\":\"MULTIPLA_ESCOLHA\",\"pergunta\":\"O regulamento foi localizado na p\u00E1gina eletr\u00F4nica?\",\"peso\":11.1099999999999994315658113919198513031005859375,\"ordem\":3,\"selecaoUnica\":true,\"respostaComplementar\":false,\"opcoesResposta\":[{\"opcao\":\"SIM\",\"peso\":100,\"resposta\":false},{\"opcao\":\"N\u00C3O\",\"peso\":0,\"resposta\":false}]}],\"nome\":\"Regulamenta\u00E7\u00E3o\",\"descricao\":null,\"peso\":25,\"obrigatorio\":false,\"ordem\":1},{\"id\":null,\"questoes\":[],\"nome\":\"Transpar\u00EAncia Passiva\",\"descricao\":null,\"peso\":75,\"obrigatorio\":false,\"ordem\":2}]";
		
		assertThat(ConversorQuestionario.toBlocos(jsonQuestionario))
			.isNotEmpty()
			.hasSize(2);
		
		// TODO: valida questoes etc
	}
	
	public void converte_blocos_para_json() {
		List<Bloco> blocos = new BlocoEbtBuilder().build();
		
		assertThat(ConversorQuestionario.toJson(blocos))
			.isNotEmpty();
		
		// TODO: validar com estrutura esperada
	}

}
