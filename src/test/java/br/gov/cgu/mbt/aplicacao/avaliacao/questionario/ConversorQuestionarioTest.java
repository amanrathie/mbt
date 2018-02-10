package br.gov.cgu.mbt.aplicacao.avaliacao.questionario;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;

import br.gov.cgu.mbt.aplicacao.avaliacao.migrador.builder.BlocoEbtBuilder;
import br.gov.cgu.mbt.negocio.avaliacao.questionario.json.Bloco;

public class ConversorQuestionarioTest {
	
	@Test
	public void converte_json_para_blocos() {
		String jsonQuestionario = "[{\"id\":null,\"questoes\":[{\"type\":\"qd\",\"id\":null,\"tipo\":\"DESCRITIVA\",\"pergunta\":\"Qual o site municipal?\",\"peso\":0,\"ordem\":1,\"obrigatoria\":false,\"resposta\":\"http://www.apiuna.sc.gov.br/\"},{\"type\":\"qd\",\"id\":null,\"tipo\":\"DESCRITIVA\",\"pergunta\":\"Qual o site eSIC?\",\"peso\":0,\"ordem\":2,\"obrigatoria\":false,\"resposta\":\"http://www.infovr.com.br/servicocidadao/Modulos/Consulta/index.php?fuseaction=TelaInicial.Show&e=19\"},{\"type\":\"qme\",\"id\":null,\"tipo\":\"MULTIPLA_ESCOLHA\",\"pergunta\":\"O regulamento foi localizado na p\u00E1gina eletr\u00F4nica?\",\"peso\":11.111100,\"ordem\":3,\"obrigatoria\":false,\"selecaoUnica\":true,\"respostaComplementar\":false,\"opcoesResposta\":[{\"opcao\":\"SIM\",\"peso\":100,\"resposta\":true},{\"opcao\":\"N\u00C3O\",\"peso\":0,\"resposta\":false}]},{\"type\":\"qme\",\"id\":null,\"tipo\":\"MULTIPLA_ESCOLHA\",\"pergunta\":\"O ente regulamentou a LAI?\",\"peso\":16.666700,\"ordem\":4,\"obrigatoria\":false,\"selecaoUnica\":true,\"respostaComplementar\":false,\"opcoesResposta\":[{\"opcao\":\"SIM\",\"peso\":100,\"resposta\":true},{\"opcao\":\"N\u00C3O\",\"peso\":0,\"resposta\":false}]},{\"type\":\"qme\",\"id\":null,\"tipo\":\"MULTIPLA_ESCOLHA\",\"pergunta\":\"Regulamentou a cria\u00E7\u00E3o do SIC?\",\"peso\":22.222200,\"ordem\":5,\"obrigatoria\":false,\"selecaoUnica\":true,\"respostaComplementar\":false,\"opcoesResposta\":[{\"opcao\":\"SIM\",\"peso\":100,\"resposta\":true},{\"opcao\":\"N\u00C3O\",\"peso\":0,\"resposta\":false}]},{\"type\":\"qme\",\"id\":null,\"tipo\":\"MULTIPLA_ESCOLHA\",\"pergunta\":\"Existe a previs\u00E3o da autoridades que podem classificar a informa\u00E7\u00E3o quanto ao grau de sigilo?\",\"peso\":16.666700,\"ordem\":6,\"obrigatoria\":false,\"selecaoUnica\":true,\"respostaComplementar\":false,\"opcoesResposta\":[{\"opcao\":\"SIM\",\"peso\":100,\"resposta\":true},{\"opcao\":\"N\u00C3O\",\"peso\":0,\"resposta\":false}]},{\"type\":\"qme\",\"id\":null,\"tipo\":\"MULTIPLA_ESCOLHA\",\"pergunta\":\"Previs\u00E3o de responsabiliza\u00E7\u00E3o do servidor em caso de condutas il\u00EDcitas;\\t\",\"peso\":11.111100,\"ordem\":7,\"obrigatoria\":false,\"selecaoUnica\":true,\"respostaComplementar\":false,\"opcoesResposta\":[{\"opcao\":\"SIM\",\"peso\":100,\"resposta\":true},{\"opcao\":\"N\u00C3O\",\"peso\":0,\"resposta\":false}]},{\"type\":\"qme\",\"id\":null,\"tipo\":\"MULTIPLA_ESCOLHA\",\"pergunta\":\"Regulamentou a exist\u00EAncia de pelo menos uma inst\u00E2ncia recursal?\\t\",\"peso\":22.222200,\"ordem\":8,\"obrigatoria\":false,\"selecaoUnica\":true,\"respostaComplementar\":false,\"opcoesResposta\":[{\"opcao\":\"SIM\",\"peso\":100,\"resposta\":true},{\"opcao\":\"N\u00C3O\",\"peso\":0,\"resposta\":false}]}],\"nome\":\"Regulamenta\u00E7\u00E3o\",\"descricao\":null,\"peso\":25,\"obrigatorio\":true,\"ordem\":1},{\"id\":null,\"questoes\":[{\"type\":\"qm\",\"id\":null,\"tipo\":\"MATRIZ\",\"pergunta\":\"Existe indica\u00E7\u00E3o precisa no site de funcionamento de um SIC fisico, ou seja, com a possibilidade de entrega de um pedido de acesso de forma presencial?\",\"peso\":18.518500,\"ordem\":1,\"obrigatoria\":false,\"opcoesMultiplaEscolha\":[{\"opcoesResposta\":[{\"opcao\":\"SIM\",\"peso\":100,\"resposta\":true},{\"opcao\":\"N\u00C3O\",\"peso\":0,\"resposta\":false}],\"pergunta\":\"Indica\u00E7\u00E3o do \u00F3rg\u00E3o?\",\"peso\":40.000000},{\"opcoesResposta\":[{\"opcao\":\"SIM\",\"peso\":100,\"resposta\":true},{\"opcao\":\"N\u00C3O\",\"peso\":0,\"resposta\":false}],\"pergunta\":\"Indica\u00E7\u00E3o de endere\u00E7o?\",\"peso\":20.000000},{\"opcoesResposta\":[{\"opcao\":\"SIM\",\"peso\":100,\"resposta\":true},{\"opcao\":\"N\u00C3O\",\"peso\":0,\"resposta\":false}],\"pergunta\":\"Indica\u00E7\u00E3o de telefone?\",\"peso\":20.000000},{\"opcoesResposta\":[{\"opcao\":\"SIM\",\"peso\":100,\"resposta\":true},{\"opcao\":\"N\u00C3O\",\"peso\":0,\"resposta\":false}],\"pergunta\":\"Indica\u00E7\u00E3o dos hor\u00E1rios de funcionamento?\",\"peso\":20.000000}]},{\"type\":\"qme\",\"id\":null,\"tipo\":\"MULTIPLA_ESCOLHA\",\"pergunta\":\"H\u00E1 alternativa de enviar pedidos de forma eletr\u00F4nica ao SIC?\",\"peso\":7.407400,\"ordem\":2,\"obrigatoria\":false,\"selecaoUnica\":true,\"respostaComplementar\":false,\"opcoesResposta\":[{\"opcao\":\"SIM\",\"peso\":100,\"resposta\":true},{\"opcao\":\"N\u00C3O\",\"peso\":0,\"resposta\":false}]}],\"nome\":\"Transpar\u00EAncia Passiva\",\"descricao\":null,\"peso\":75,\"obrigatorio\":true,\"ordem\":2}]\r\n";
		
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
