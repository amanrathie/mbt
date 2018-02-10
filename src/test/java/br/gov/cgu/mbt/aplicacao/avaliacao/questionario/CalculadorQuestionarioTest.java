package br.gov.cgu.mbt.aplicacao.avaliacao.questionario;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.Arrays;

import org.junit.Test;

import br.gov.cgu.mbt.aplicacao.avaliacao.migrador.util.EbtUtil;
import br.gov.cgu.mbt.negocio.avaliacao.questionario.json.Bloco;
import br.gov.cgu.mbt.negocio.avaliacao.questionario.json.OpcaoResposta;
import br.gov.cgu.mbt.negocio.avaliacao.questionario.json.QuestaoMultiplaEscolha;

public class CalculadorQuestionarioTest {
	
	private CalculadorQuestionario calculador = new CalculadorQuestionario();
	
	
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
		
		assertThat(notaFinal).isEqualByComparingTo(new BigDecimal(100));
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
		
		assertThat(notaFinal).isEqualByComparingTo(new BigDecimal(50));
	}

}
