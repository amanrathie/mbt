package br.gov.cgu.mbt.integracao;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.CSVRecord;
import org.assertj.core.api.SoftAssertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.gov.cgu.mbt.Constantes;
import br.gov.cgu.mbt.aplicacao.avaliacao.migrador.MigradorArquivoRespostaParser;
import br.gov.cgu.mbt.aplicacao.avaliacao.migrador.MigradorAvaliacaoService;
import br.gov.cgu.mbt.aplicacao.avaliacao.migrador.util.QuestionarioEbtHeader;
import br.gov.cgu.mbt.aplicacao.avaliacao.resultado.ResultadoAvaliacaoRepository;
import br.gov.cgu.mbt.negocio.avaliacao.Avaliacao;
import br.gov.cgu.mbt.negocio.avaliacao.resultado.ResultadoAvaliacao;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MigradorAvaliacaoEbtIntegracaoIT {
	
	@Autowired
	private MigradorAvaliacaoService migradorAvaliacaoEbtService;
	
	@Autowired
	private ResultadoAvaliacaoRepository resultadoAvaliacaoRepository;
	
	private SoftAssertions softAssertions;
	
	@Before
	public void setUp() {
		softAssertions = new SoftAssertions();
	}

	@After
	public void tearDown() {
		softAssertions.assertAll();
	}
	 
	@Test
	public void avaliacoes_migradas_corretamente() throws Exception {
		List<Avaliacao> avaliacoes = migradorAvaliacaoEbtService.criarAvaliacoesIndependentes();

		for (Avaliacao avaliacao : avaliacoes) {
			MigradorArquivoRespostaParser parser = new MigradorArquivoRespostaParser(Constantes.ARQUIVO_EBT_MIGRACAO);
			Map<String, BigDecimal> municipioNotaArquivo = getMunicipioNotaArquivo(avaliacao);
			
			List<ResultadoAvaliacao> resultados = resultadoAvaliacaoRepository.getPorIdAvaliacao(avaliacao.getId());
			
			for (ResultadoAvaliacao resultado : resultados) {
				BigDecimal notaCalculada = municipioNotaArquivo.get(resultado.getUf()+"_"+resultado.getNomeMunicipio());
				softAssertions.assertThat(notaCalculada.compareTo(resultado.getNota()))
				.as("Municipio: %s - %s na ediçao %d", resultado.getUf(), resultado.getNomeMunicipio(), resultado.getAvaliacao().getEdicao())
				.isEqualTo(0);
			}
		}
	}
	
	private Map<String, BigDecimal> getMunicipioNotaArquivo(Avaliacao avaliacao) {
		// Logica para salvar dados do .CSV caso o desvio seja de 0,01
		MigradorArquivoRespostaParser parser = new MigradorArquivoRespostaParser(Constantes.ARQUIVO_EBT_MIGRACAO);
		Map<String, BigDecimal> municipioNota = new HashMap<String, BigDecimal>();
		List<CSVRecord> records = parser.parse(avaliacao.getEdicao());
		for (CSVRecord record : records) { // TODO: colocar codigo do IBGE no mapa depois que tiver o SQL populado
			String uf = record.get(QuestionarioEbtHeader.uf);
			String municipio = record.get(QuestionarioEbtHeader.municipio);
			String nota = record.get(QuestionarioEbtHeader.nota);
			
			municipioNota.put(uf+"_"+municipio, new BigDecimal(nota));
		}
		
		return municipioNota;
	}
}
