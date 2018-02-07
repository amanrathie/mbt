package br.gov.cgu.mbt.integracao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.gov.cgu.mbt.aplicacao.avaliacao.migrador.MigradorAvaliacaoEbtService;
import br.gov.cgu.mbt.negocio.avaliacao.Avaliacao;
import br.gov.cgu.mbt.negocio.avaliacao.AvaliacaoRepository;
import br.gov.cgu.mbt.negocio.avaliacao.questionario.Questionario;
import br.gov.cgu.mbt.negocio.avaliacao.questionario.json.Bloco;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MigradorAvaliacaoEbtIntegracaoIT {
	
	/*private EBTRespostaParser ebtQuestionarioParser = 
			new EBTRespostaParser("ebt/ebt_questionario.csv");*/
	
	@Autowired
	private MigradorAvaliacaoEbtService migradorAvaliacaoEbtService;
	
	@Autowired
	private AvaliacaoRepository avaliacaoRepository;
	
	@Test
	public void avaliacoes_migradas_corretamente() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		migradorAvaliacaoEbtService.criarAvaliacoesIndependentes();
		
		List<Avaliacao> avaliacoes = avaliacaoRepository.getAll();
		
		assertThat(avaliacoes).isNotEmpty();
		for (Avaliacao avaliacao : avaliacoes) {
			Questionario questionario = avaliacao.getQuestionario();
			assertThat(questionario).isNotNull();
			
			String jsonQuestionario = questionario.getEstrutura();
			
			List<Bloco> blocos = mapper.readValue(jsonQuestionario, mapper.getTypeFactory().constructCollectionType(List.class, Bloco.class));

			assertThat(blocos)
			.isNotEmpty()
			.hasSize(2);

		}
		// TODO: contém blocos, questões etc
	}
}
