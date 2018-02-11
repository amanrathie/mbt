package br.gov.cgu.mbt.integracao;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.csv.CSVRecord;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

import br.gov.cgu.mbt.aplicacao.avaliacao.migrador.MigradorAvaliacaoEbtService;
import br.gov.cgu.mbt.aplicacao.avaliacao.migrador.RespostaEbtParser;
import br.gov.cgu.mbt.aplicacao.avaliacao.migrador.util.QuestionarioEbtHeader;
import br.gov.cgu.mbt.negocio.avaliacao.Avaliacao;
import br.gov.cgu.mbt.negocio.avaliacao.AvaliacaoRepository;
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
		
		RespostaEbtParser parser = new RespostaEbtParser("/ebt/ebt_respostas.csv");
		
		for (Avaliacao avaliacao : avaliacoes) {
			List<CSVRecord> records = parser.parse(avaliacao.getEdicao());
			
			Map<String, BigDecimal> municipioNota = records.stream()
			.filter(e -> !e.get(QuestionarioEbtHeader.municipio).equalsIgnoreCase("ESTADO")) // retira os estados
			.collect(Collectors.toList())
			.stream()
			.collect(Collectors.toMap(e -> e.get(QuestionarioEbtHeader.municipio), e -> new BigDecimal(e.get(QuestionarioEbtHeader.nota))));
			
			List<ResultadoAvaliacao> resultados = resultadoAvaliacaoRepository.getPorIdAvaliacao(avaliacao.getId());
			
			for (ResultadoAvaliacao resultado : resultados) {
				assertThat(municipioNota.get(resultado.getNomeMunicipio())).isEqualByComparingTo(resultado.getNota());
			}
			
			
		}
		
		/*assertThat(avaliacoes).isNotEmpty();
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

		}*/
		
		/*List<ResultadoAvaliacao> resultadoDeApiuna = resultadoAvaliacaoRepository.getPorNomeMunicipio("Apiuna");
		assertThat(resultadoDeApiuna).isNotEmpty();
		assertThat(resultadoDeApiuna.get(0).getNota()).isEqualTo(new BigDecimal(10));*/

		// TODO: contém blocos, questões etc
	}
}
