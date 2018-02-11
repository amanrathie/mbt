package br.gov.cgu.mbt.integracao;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.gov.cgu.mbt.aplicacao.avaliacao.migrador.MigradorAvaliacaoEbtService;
import br.gov.cgu.mbt.aplicacao.avaliacao.migrador.util.EbtUtil;
import br.gov.cgu.mbt.aplicacao.avaliacao.questionario.ConversorQuestionario;
import br.gov.cgu.mbt.negocio.avaliacao.Avaliacao;
import br.gov.cgu.mbt.negocio.avaliacao.AvaliacaoRepository;
import br.gov.cgu.mbt.negocio.avaliacao.questionario.Questionario;
import br.gov.cgu.mbt.negocio.avaliacao.questionario.json.Bloco;
import br.gov.cgu.mbt.negocio.avaliacao.resultado.ResultadoAvaliacao;
import br.gov.cgu.mbt.negocio.avaliacao.resultado.ResultadoAvaliacaoRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MigradorAvaliacaoEbtIntegracaoIT {
	
	@Autowired
	private MigradorAvaliacaoEbtService migradorAvaliacaoEbtService;
	
	@Autowired
	private ResultadoAvaliacaoRepository resultadoAvaliacaoRepository;
	
	@Autowired
	private AvaliacaoRepository avaliacaoRepository;
	
	@Test
	public void avaliacoes_migradas_corretamente() throws Exception {
		migradorAvaliacaoEbtService.criarAvaliacoesIndependentes();
		
		List<Avaliacao> avaliacoes = avaliacaoRepository.getAll();
		
		assertThat(avaliacoes).isNotEmpty();
		for (Avaliacao avaliacao : avaliacoes) {
			Questionario questionario = avaliacao.getQuestionario();
			assertThat(questionario).isNotNull();
			
			String jsonQuestionario = questionario.getEstrutura();
			
			List<Bloco> blocos = ConversorQuestionario.toBlocos(jsonQuestionario);

			assertThat(blocos)
			.isNotEmpty()
			.hasSize(2);
			
			for (Bloco bloco : blocos) {
				if (bloco.getNome().equals(EbtUtil.BLOCO_REGULAMENTACAO)) {
					assertThat(bloco.getQuestoes().size()).isEqualTo(8);
				} else if (bloco.getNome().equals(EbtUtil.BLOCO_TR_PASSIVA)) {
					assertThat(bloco.getQuestoes().size()).isEqualTo(6);
				}
			}

		}
		
		/*List<ResultadoAvaliacao> resultadoDeApiuna = resultadoAvaliacaoRepository.getPorNomeMunicipio("Apiuna");
		assertThat(resultadoDeApiuna).isNotEmpty();
		assertThat(resultadoDeApiuna.get(0).getNota()).isEqualTo(new BigDecimal(10));*/

		// TODO: contém blocos, questões etc
	}
}
