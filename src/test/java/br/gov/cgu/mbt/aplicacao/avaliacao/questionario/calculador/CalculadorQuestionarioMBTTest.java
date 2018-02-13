package br.gov.cgu.mbt.aplicacao.avaliacao.questionario.calculador;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import br.gov.cgu.mbt.aplicacao.avaliacao.migrador.util.EbtUtil;
import br.gov.cgu.mbt.aplicacao.avaliacao.questionario.ConversorQuestionario;
import br.gov.cgu.mbt.negocio.avaliacao.questionario.json.Bloco;
import br.gov.cgu.mbt.negocio.avaliacao.questionario.json.OpcaoResposta;
import br.gov.cgu.mbt.negocio.avaliacao.questionario.json.QuestaoMultiplaEscolha;

public class CalculadorQuestionarioMBTTest {
	
	private CalculadorQuestionario calculador = new CalculadorQuestionarioMBT(); // TODO: Criar testador de cada tipo
	
	
	// TODO: testar diferentes cenarios
	@Test
	public void calcula_nota_corretamente() {
		Bloco bloco1 = 
				Bloco.builder()
					.peso(new BigDecimal(100))
					.questao(QuestaoMultiplaEscolha.builder()
						.peso(new BigDecimal(100))
						.opcaoResposta(OpcaoResposta.builder()
								.opcao(EbtUtil.OPCAO_SIM)
								.peso(new BigDecimal(100))
								.isResposta(true)
								.build())
						.opcaoResposta(OpcaoResposta.builder()
								.opcao(EbtUtil.OPCAO_NAO)
								.peso(BigDecimal.ZERO)
								.build())
					.build())
				.build();
		
		BigDecimal notaFinal = calculador.calculaNota(Arrays.asList(bloco1));
		
		assertThat(notaFinal).isEqualTo(new BigDecimal("10.00"));
	}
	
	@Test
	public void calcula_nota_corretamente_2() {
		Bloco bloco1 = 
				Bloco.builder()
					.peso(new BigDecimal(50))
					.questao(QuestaoMultiplaEscolha.builder()
						.peso(new BigDecimal(100))
						.opcaoResposta(OpcaoResposta.builder()
								.opcao(EbtUtil.OPCAO_SIM)
								.peso(new BigDecimal(100))
								.isResposta(true)
								.build())
						.opcaoResposta(OpcaoResposta.builder()
								.opcao(EbtUtil.OPCAO_NAO)
								.peso(BigDecimal.ZERO)
								.build())
					.build())
				.build();
		
		Bloco bloco2 = 
				Bloco.builder()
					.peso(new BigDecimal(50))
					.questao(QuestaoMultiplaEscolha.builder()
						.peso(new BigDecimal(100))
						.opcaoResposta(OpcaoResposta.builder()
								.opcao(EbtUtil.OPCAO_SIM)
								.peso(new BigDecimal(100))
								.build())
						.opcaoResposta(OpcaoResposta.builder()
								.opcao(EbtUtil.OPCAO_NAO)
								.peso(BigDecimal.ZERO)
								.isResposta(true)
								.build())
					.build())
				.build();
		
		BigDecimal notaFinal = calculador.calculaNota(Arrays.asList(bloco1, bloco2));
		
		assertThat(notaFinal).isEqualTo(new BigDecimal("5.00"));
	}
	
	@Test
	public void calcula_nota_corretamente_3() {
		List<Bloco> blocos = ConversorQuestionario.toBlocos("[{\r\n\t\"id\": null,\r\n\t\"questoes\": [{\r\n\t\t\"type\": \"qd\",\r\n\t\t\"id\": null,\r\n\t\t\"tipo\": \"DESCRITIVA\",\r\n\t\t\"pergunta\": \"Qual o site municipal?\",\r\n\t\t\"peso\": 0,\r\n\t\t\"ordem\": 1,\r\n\t\t\"obrigatoria\": false,\r\n\t\t\"resposta\": \"http://www.apiuna.sc.gov.br/\"\r\n\t}, {\r\n\t\t\"type\": \"qd\",\r\n\t\t\"id\": null,\r\n\t\t\"tipo\": \"DESCRITIVA\",\r\n\t\t\"pergunta\": \"Qual o site eSIC?\",\r\n\t\t\"peso\": 0,\r\n\t\t\"ordem\": 2,\r\n\t\t\"obrigatoria\": false,\r\n\t\t\"resposta\": \"http://www.infovr.com.br/servicocidadao/Modulos/Consulta/index.php?fuseaction=TelaInicial.Show&e=19\"\r\n\t}, {\r\n\t\t\"type\": \"qme\",\r\n\t\t\"id\": null,\r\n\t\t\"tipo\": \"MULTIPLA_ESCOLHA\",\r\n\t\t\"pergunta\": \"O regulamento foi localizado na p\u00E1gina eletr\u00F4nica?\",\r\n\t\t\"peso\": 11.111100,\r\n\t\t\"ordem\": 3,\r\n\t\t\"obrigatoria\": false,\r\n\t\t\"selecaoUnica\": true,\r\n\t\t\"respostaComplementar\": false,\r\n\t\t\"opcoesResposta\": [{\r\n\t\t\t\"opcao\": \"SIM\",\r\n\t\t\t\"peso\": 100,\r\n\t\t\t\"resposta\": true\r\n\t\t}, {\r\n\t\t\t\"opcao\": \"N\u00C3O\",\r\n\t\t\t\"peso\": 0,\r\n\t\t\t\"resposta\": false\r\n\t\t}]\r\n\t}, {\r\n\t\t\"type\": \"qme\",\r\n\t\t\"id\": null,\r\n\t\t\"tipo\": \"MULTIPLA_ESCOLHA\",\r\n\t\t\"pergunta\": \"O ente regulamentou a LAI?\",\r\n\t\t\"peso\": 16.666700,\r\n\t\t\"ordem\": 4,\r\n\t\t\"obrigatoria\": false,\r\n\t\t\"selecaoUnica\": true,\r\n\t\t\"respostaComplementar\": false,\r\n\t\t\"opcoesResposta\": [{\r\n\t\t\t\"opcao\": \"SIM\",\r\n\t\t\t\"peso\": 100,\r\n\t\t\t\"resposta\": true\r\n\t\t}, {\r\n\t\t\t\"opcao\": \"N\u00C3O\",\r\n\t\t\t\"peso\": 0,\r\n\t\t\t\"resposta\": false\r\n\t\t}]\r\n\t}, {\r\n\t\t\"type\": \"qme\",\r\n\t\t\"id\": null,\r\n\t\t\"tipo\": \"MULTIPLA_ESCOLHA\",\r\n\t\t\"pergunta\": \"Regulamentou a cria\u00E7\u00E3o do SIC?\",\r\n\t\t\"peso\": 22.222200,\r\n\t\t\"ordem\": 5,\r\n\t\t\"obrigatoria\": false,\r\n\t\t\"selecaoUnica\": true,\r\n\t\t\"respostaComplementar\": false,\r\n\t\t\"opcoesResposta\": [{\r\n\t\t\t\"opcao\": \"SIM\",\r\n\t\t\t\"peso\": 100,\r\n\t\t\t\"resposta\": true\r\n\t\t}, {\r\n\t\t\t\"opcao\": \"N\u00C3O\",\r\n\t\t\t\"peso\": 0,\r\n\t\t\t\"resposta\": false\r\n\t\t}]\r\n\t}, {\r\n\t\t\"type\": \"qme\",\r\n\t\t\"id\": null,\r\n\t\t\"tipo\": \"MULTIPLA_ESCOLHA\",\r\n\t\t\"pergunta\": \"Existe a previs\u00E3o da autoridades que podem classificar a informa\u00E7\u00E3o quanto ao grau de sigilo?\",\r\n\t\t\"peso\": 16.666700,\r\n\t\t\"ordem\": 6,\r\n\t\t\"obrigatoria\": false,\r\n\t\t\"selecaoUnica\": true,\r\n\t\t\"respostaComplementar\": false,\r\n\t\t\"opcoesResposta\": [{\r\n\t\t\t\"opcao\": \"SIM\",\r\n\t\t\t\"peso\": 100,\r\n\t\t\t\"resposta\": true\r\n\t\t}, {\r\n\t\t\t\"opcao\": \"N\u00C3O\",\r\n\t\t\t\"peso\": 0,\r\n\t\t\t\"resposta\": false\r\n\t\t}]\r\n\t}, {\r\n\t\t\"type\": \"qme\",\r\n\t\t\"id\": null,\r\n\t\t\"tipo\": \"MULTIPLA_ESCOLHA\",\r\n\t\t\"pergunta\": \"Previs\u00E3o de responsabiliza\u00E7\u00E3o do servidor em caso de condutas il\u00EDcitas;\\t\",\r\n\t\t\"peso\": 11.111100,\r\n\t\t\"ordem\": 7,\r\n\t\t\"obrigatoria\": false,\r\n\t\t\"selecaoUnica\": true,\r\n\t\t\"respostaComplementar\": false,\r\n\t\t\"opcoesResposta\": [{\r\n\t\t\t\"opcao\": \"SIM\",\r\n\t\t\t\"peso\": 100,\r\n\t\t\t\"resposta\": true\r\n\t\t}, {\r\n\t\t\t\"opcao\": \"N\u00C3O\",\r\n\t\t\t\"peso\": 0,\r\n\t\t\t\"resposta\": false\r\n\t\t}]\r\n\t}, {\r\n\t\t\"type\": \"qme\",\r\n\t\t\"id\": null,\r\n\t\t\"tipo\": \"MULTIPLA_ESCOLHA\",\r\n\t\t\"pergunta\": \"Regulamentou a exist\u00EAncia de pelo menos uma inst\u00E2ncia recursal?\\t\",\r\n\t\t\"peso\": 22.222200,\r\n\t\t\"ordem\": 8,\r\n\t\t\"obrigatoria\": false,\r\n\t\t\"selecaoUnica\": true,\r\n\t\t\"respostaComplementar\": false,\r\n\t\t\"opcoesResposta\": [{\r\n\t\t\t\"opcao\": \"SIM\",\r\n\t\t\t\"peso\": 100,\r\n\t\t\t\"resposta\": true\r\n\t\t}, {\r\n\t\t\t\"opcao\": \"N\u00C3O\",\r\n\t\t\t\"peso\": 0,\r\n\t\t\t\"resposta\": false\r\n\t\t}]\r\n\t}],\r\n\t\"nome\": \"Regulamenta\u00E7\u00E3o\",\r\n\t\"descricao\": null,\r\n\t\"peso\": 25,\r\n\t\"obrigatorio\": true,\r\n\t\"ordem\": 1\r\n}, {\r\n\t\"id\": null,\r\n\t\"questoes\": [{\r\n\t\t\"type\": \"qm\",\r\n\t\t\"id\": null,\r\n\t\t\"tipo\": \"MATRIZ\",\r\n\t\t\"pergunta\": \"Existe indica\u00E7\u00E3o precisa no site de funcionamento de um SIC fisico, ou seja, com a possibilidade de entrega de um pedido de acesso de forma presencial?\",\r\n\t\t\"peso\": 18.518500,\r\n\t\t\"ordem\": 1,\r\n\t\t\"obrigatoria\": false,\r\n\t\t\"opcoesMultiplaEscolha\": [{\r\n\t\t\t\"opcoesResposta\": [{\r\n\t\t\t\t\"opcao\": \"SIM\",\r\n\t\t\t\t\"peso\": 100,\r\n\t\t\t\t\"resposta\": true\r\n\t\t\t}, {\r\n\t\t\t\t\"opcao\": \"N\u00C3O\",\r\n\t\t\t\t\"peso\": 0,\r\n\t\t\t\t\"resposta\": false\r\n\t\t\t}],\r\n\t\t\t\"pergunta\": \"Indica\u00E7\u00E3o do \u00F3rg\u00E3o?\",\r\n\t\t\t\"peso\": 40.000000,\r\n\t\t\t\"ordem\": 1\r\n\t\t}, {\r\n\t\t\t\"opcoesResposta\": [{\r\n\t\t\t\t\"opcao\": \"SIM\",\r\n\t\t\t\t\"peso\": 100,\r\n\t\t\t\t\"resposta\": true\r\n\t\t\t}, {\r\n\t\t\t\t\"opcao\": \"N\u00C3O\",\r\n\t\t\t\t\"peso\": 0,\r\n\t\t\t\t\"resposta\": false\r\n\t\t\t}],\r\n\t\t\t\"pergunta\": \"Indica\u00E7\u00E3o de endere\u00E7o?\",\r\n\t\t\t\"peso\": 20.000000,\r\n\t\t\t\"ordem\": 2\r\n\t\t}, {\r\n\t\t\t\"opcoesResposta\": [{\r\n\t\t\t\t\"opcao\": \"SIM\",\r\n\t\t\t\t\"peso\": 100,\r\n\t\t\t\t\"resposta\": true\r\n\t\t\t}, {\r\n\t\t\t\t\"opcao\": \"N\u00C3O\",\r\n\t\t\t\t\"peso\": 0,\r\n\t\t\t\t\"resposta\": false\r\n\t\t\t}],\r\n\t\t\t\"pergunta\": \"Indica\u00E7\u00E3o de telefone?\",\r\n\t\t\t\"peso\": 20.000000,\r\n\t\t\t\"ordem\": 3\r\n\t\t}, {\r\n\t\t\t\"opcoesResposta\": [{\r\n\t\t\t\t\"opcao\": \"SIM\",\r\n\t\t\t\t\"peso\": 100,\r\n\t\t\t\t\"resposta\": true\r\n\t\t\t}, {\r\n\t\t\t\t\"opcao\": \"N\u00C3O\",\r\n\t\t\t\t\"peso\": 0,\r\n\t\t\t\t\"resposta\": false\r\n\t\t\t}],\r\n\t\t\t\"pergunta\": \"Indica\u00E7\u00E3o dos hor\u00E1rios de funcionamento?\",\r\n\t\t\t\"peso\": 20.000000,\r\n\t\t\t\"ordem\": 4\r\n\t\t}]\r\n\t}, {\r\n\t\t\"type\": \"qme\",\r\n\t\t\"id\": null,\r\n\t\t\"tipo\": \"MULTIPLA_ESCOLHA\",\r\n\t\t\"pergunta\": \"H\u00E1 alternativa de enviar pedidos de forma eletr\u00F4nica ao SIC?\",\r\n\t\t\"peso\": 7.407400,\r\n\t\t\"ordem\": 2,\r\n\t\t\"obrigatoria\": false,\r\n\t\t\"selecaoUnica\": true,\r\n\t\t\"respostaComplementar\": false,\r\n\t\t\"opcoesResposta\": [{\r\n\t\t\t\"opcao\": \"SIM\",\r\n\t\t\t\"peso\": 100,\r\n\t\t\t\"resposta\": true\r\n\t\t}, {\r\n\t\t\t\"opcao\": \"N\u00C3O\",\r\n\t\t\t\"peso\": 0,\r\n\t\t\t\"resposta\": false\r\n\t\t}]\r\n\t}, {\r\n\t\t\"type\": \"qm\",\r\n\t\t\"id\": null,\r\n\t\t\"tipo\": \"MATRIZ\",\r\n\t\t\"pergunta\": \"Para fazer a solicita\u00E7\u00E3o, s\u00E3o exigidos dados de  identifica\u00E7\u00E3o do requerente que dificultem ou impossibilitem o acesso \u00E0 informa\u00E7\u00E3o?\",\r\n\t\t\"peso\": 11.111100,\r\n\t\t\"ordem\": 3,\r\n\t\t\"obrigatoria\": false,\r\n\t\t\"opcoesMultiplaEscolha\": [{\r\n\t\t\t\"opcoesResposta\": [{\r\n\t\t\t\t\"opcao\": \"SIM\",\r\n\t\t\t\t\"peso\": 100,\r\n\t\t\t\t\"resposta\": true\r\n\t\t\t}, {\r\n\t\t\t\t\"opcao\": \"N\u00C3O\",\r\n\t\t\t\t\"peso\": 0,\r\n\t\t\t\t\"resposta\": false\r\n\t\t\t}],\r\n\t\t\t\"pergunta\": \"Envio de qualquer documento para cadastro (PF e PJ)\",\r\n\t\t\t\"peso\": 20.000000,\r\n\t\t\t\"ordem\": 1\r\n\t\t}, {\r\n\t\t\t\"opcoesResposta\": [{\r\n\t\t\t\t\"opcao\": \"SIM\",\r\n\t\t\t\t\"peso\": 100,\r\n\t\t\t\t\"resposta\": true\r\n\t\t\t}, {\r\n\t\t\t\t\"opcao\": \"N\u00C3O\",\r\n\t\t\t\t\"peso\": 0,\r\n\t\t\t\t\"resposta\": false\r\n\t\t\t}],\r\n\t\t\t\"pergunta\": \"Assinatura reconhecida\",\r\n\t\t\t\"peso\": 20.000000,\r\n\t\t\t\"ordem\": 2\r\n\t\t}, {\r\n\t\t\t\"opcoesResposta\": [{\r\n\t\t\t\t\"opcao\": \"SIM\",\r\n\t\t\t\t\"peso\": 100,\r\n\t\t\t\t\"resposta\": true\r\n\t\t\t}, {\r\n\t\t\t\t\"opcao\": \"N\u00C3O\",\r\n\t\t\t\t\"peso\": 0,\r\n\t\t\t\t\"resposta\": false\r\n\t\t\t}],\r\n\t\t\t\"pergunta\": \"Declara\u00E7\u00E3o de responsabilidade\",\r\n\t\t\t\"peso\": 20.000000,\r\n\t\t\t\"ordem\": 3\r\n\t\t}, {\r\n\t\t\t\"opcoesResposta\": [{\r\n\t\t\t\t\"opcao\": \"SIM\",\r\n\t\t\t\t\"peso\": 100,\r\n\t\t\t\t\"resposta\": true\r\n\t\t\t}, {\r\n\t\t\t\t\"opcao\": \"N\u00C3O\",\r\n\t\t\t\t\"peso\": 0,\r\n\t\t\t\t\"resposta\": false\r\n\t\t\t}],\r\n\t\t\t\"pergunta\": \"Maioridade\",\r\n\t\t\t\"peso\": 20.000000,\r\n\t\t\t\"ordem\": 4\r\n\t\t}, {\r\n\t\t\t\"opcoesResposta\": [{\r\n\t\t\t\t\"opcao\": \"SIM\",\r\n\t\t\t\t\"peso\": 100,\r\n\t\t\t\t\"resposta\": true\r\n\t\t\t}, {\r\n\t\t\t\t\"opcao\": \"N\u00C3O\",\r\n\t\t\t\t\"peso\": 0,\r\n\t\t\t\t\"resposta\": false\r\n\t\t\t}],\r\n\t\t\t\"pergunta\": \"Outras exig\u00EAncias\",\r\n\t\t\t\"peso\": 20.000000,\r\n\t\t\t\"ordem\": 5\r\n\t\t}]\r\n\t}, {\r\n\t\t\"type\": \"qme\",\r\n\t\t\"id\": null,\r\n\t\t\"tipo\": \"MULTIPLA_ESCOLHA\",\r\n\t\t\"pergunta\": \"Apresenta possibilidade de acompanhamento posterior da solicita\u00E7\u00E3o?\",\r\n\t\t\"peso\": 7.407400,\r\n\t\t\"ordem\": 4,\r\n\t\t\"obrigatoria\": false,\r\n\t\t\"selecaoUnica\": true,\r\n\t\t\"respostaComplementar\": false,\r\n\t\t\"opcoesResposta\": [{\r\n\t\t\t\"opcao\": \"SIM\",\r\n\t\t\t\"peso\": 100,\r\n\t\t\t\"resposta\": true\r\n\t\t}, {\r\n\t\t\t\"opcao\": \"N\u00C3O\",\r\n\t\t\t\"peso\": 0,\r\n\t\t\t\"resposta\": false\r\n\t\t}]\r\n\t}, {\r\n\t\t\"type\": \"qm\",\r\n\t\t\"id\": null,\r\n\t\t\"tipo\": \"MATRIZ\",\r\n\t\t\"pergunta\": \"Cumpre os prazos para resposta das solicita\u00E7\u00F5es?\",\r\n\t\t\"peso\": 18.518500,\r\n\t\t\"ordem\": 5,\r\n\t\t\"obrigatoria\": false,\r\n\t\t\"opcoesMultiplaEscolha\": [{\r\n\t\t\t\"opcoesResposta\": [{\r\n\t\t\t\t\"opcao\": \"SIM\",\r\n\t\t\t\t\"peso\": 100,\r\n\t\t\t\t\"resposta\": true\r\n\t\t\t}, {\r\n\t\t\t\t\"opcao\": \"N\u00C3O\",\r\n\t\t\t\t\"peso\": 0,\r\n\t\t\t\t\"resposta\": false\r\n\t\t\t}],\r\n\t\t\t\"pergunta\": \"Resposta no prazo da 1\u00AA pergunta: Questionamento na \u00E1rea de Sa\u00FAde\",\r\n\t\t\t\"peso\": 20.000000,\r\n\t\t\t\"ordem\": 1\r\n\t\t}, {\r\n\t\t\t\"opcoesResposta\": [{\r\n\t\t\t\t\"opcao\": \"SIM\",\r\n\t\t\t\t\"peso\": 100,\r\n\t\t\t\t\"resposta\": true\r\n\t\t\t}, {\r\n\t\t\t\t\"opcao\": \"N\u00C3O\",\r\n\t\t\t\t\"peso\": 0,\r\n\t\t\t\t\"resposta\": false\r\n\t\t\t}],\r\n\t\t\t\"pergunta\": \"Resposta no prazo da 2\u00AA pergunta: Questionamento na \u00E1rea de Educa\u00E7\u00E3o\",\r\n\t\t\t\"peso\": 20.000000,\r\n\t\t\t\"ordem\": 2\r\n\t\t}, {\r\n\t\t\t\"opcoesResposta\": [{\r\n\t\t\t\t\"opcao\": \"SIM\",\r\n\t\t\t\t\"peso\": 100,\r\n\t\t\t\t\"resposta\": true\r\n\t\t\t}, {\r\n\t\t\t\t\"opcao\": \"N\u00C3O\",\r\n\t\t\t\t\"peso\": 0,\r\n\t\t\t\t\"resposta\": false\r\n\t\t\t}],\r\n\t\t\t\"pergunta\": \"Resposta no prazo da 3\u00AA pergunta: Questionamento na \u00E1rea de Assist\u00EAncia Social\",\r\n\t\t\t\"peso\": 30.000000,\r\n\t\t\t\"ordem\": 3\r\n\t\t}, {\r\n\t\t\t\"opcoesResposta\": [{\r\n\t\t\t\t\"opcao\": \"SIM\",\r\n\t\t\t\t\"peso\": 100,\r\n\t\t\t\t\"resposta\": true\r\n\t\t\t}, {\r\n\t\t\t\t\"opcao\": \"N\u00C3O\",\r\n\t\t\t\t\"peso\": 0,\r\n\t\t\t\t\"resposta\": false\r\n\t\t\t}],\r\n\t\t\t\"pergunta\": \"Resposta no prazo da 4\u00AA pergunta: Norma de regulamenta\u00E7\u00E3o da LAI.\",\r\n\t\t\t\"peso\": 30.000000,\r\n\t\t\t\"ordem\": 4\r\n\t\t}]\r\n\t}, {\r\n\t\t\"type\": \"qm\",\r\n\t\t\"id\": null,\r\n\t\t\"tipo\": \"MATRIZ\",\r\n\t\t\"pergunta\": \"Respondeu ao que se perguntou, atendendo ao pedido de informa\u00E7\u00E3o? \",\r\n\t\t\"peso\": 37.037000,\r\n\t\t\"ordem\": 1,\r\n\t\t\"obrigatoria\": false,\r\n\t\t\"opcoesMultiplaEscolha\": [{\r\n\t\t\t\"opcoesResposta\": [{\r\n\t\t\t\t\"opcao\": \"SIM\",\r\n\t\t\t\t\"peso\": 100,\r\n\t\t\t\t\"resposta\": true\r\n\t\t\t}, {\r\n\t\t\t\t\"opcao\": \"N\u00C3O\",\r\n\t\t\t\t\"peso\": 0,\r\n\t\t\t\t\"resposta\": false\r\n\t\t\t}],\r\n\t\t\t\"pergunta\": \"Resposta em conformidade da 1\u00AA pergunta: Questionamento na \u00E1rea de  Sa\u00FAde\",\r\n\t\t\t\"peso\": 20.000000,\r\n\t\t\t\"ordem\": 1\r\n\t\t}, {\r\n\t\t\t\"opcoesResposta\": [{\r\n\t\t\t\t\"opcao\": \"SIM\",\r\n\t\t\t\t\"peso\": 100,\r\n\t\t\t\t\"resposta\": true\r\n\t\t\t}, {\r\n\t\t\t\t\"opcao\": \"N\u00C3O\",\r\n\t\t\t\t\"peso\": 0,\r\n\t\t\t\t\"resposta\": false\r\n\t\t\t}],\r\n\t\t\t\"pergunta\": \"Resposta em conformidade da 2\u00AA pergunta: Questionamento na \u00E1rea de  Educa\u00E7\u00E3o\",\r\n\t\t\t\"peso\": 20.000000,\r\n\t\t\t\"ordem\": 2\r\n\t\t}, {\r\n\t\t\t\"opcoesResposta\": [{\r\n\t\t\t\t\"opcao\": \"SIM\",\r\n\t\t\t\t\"peso\": 100,\r\n\t\t\t\t\"resposta\": true\r\n\t\t\t}, {\r\n\t\t\t\t\"opcao\": \"N\u00C3O\",\r\n\t\t\t\t\"peso\": 0,\r\n\t\t\t\t\"resposta\": false\r\n\t\t\t}],\r\n\t\t\t\"pergunta\": \"Resposta em conformidade da 3\u00AA pergunta: Questionamento na \u00E1rea de Assit\u00EAncia Social\",\r\n\t\t\t\"peso\": 30.000000,\r\n\t\t\t\"ordem\": 3\r\n\t\t}, {\r\n\t\t\t\"opcoesResposta\": [{\r\n\t\t\t\t\"opcao\": \"SIM\",\r\n\t\t\t\t\"peso\": 100,\r\n\t\t\t\t\"resposta\": true\r\n\t\t\t}, {\r\n\t\t\t\t\"opcao\": \"N\u00C3O\",\r\n\t\t\t\t\"peso\": 0,\r\n\t\t\t\t\"resposta\": false\r\n\t\t\t}],\r\n\t\t\t\"pergunta\": \"Resposta em conformidade da 4\u00AA pergunta: Norma de regulamenta\u00E7\u00E3o da LAI.\",\r\n\t\t\t\"peso\": 30.000000,\r\n\t\t\t\"ordem\": 4\r\n\t\t}]\r\n\t}],\r\n\t\"nome\": \"Transpar\u00EAncia Passiva\",\r\n\t\"descricao\": null,\r\n\t\"peso\": 75,\r\n\t\"obrigatorio\": true,\r\n\t\"ordem\": 2\r\n}]");
	
		BigDecimal notaFinal = calculador.calculaNota(blocos);
		assertThat(notaFinal).isEqualTo(new BigDecimal("10.00"));
	}

}
