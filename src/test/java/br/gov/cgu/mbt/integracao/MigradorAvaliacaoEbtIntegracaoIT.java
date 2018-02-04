package br.gov.cgu.mbt.integracao;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.gov.cgu.mbt.aplicacao.avaliacao.migrador.MigradorAvaliacaoEbtService;
import br.gov.cgu.mbt.negocio.avaliacao.Avaliacao;
import br.gov.cgu.mbt.negocio.avaliacao.AvaliacaoRepository;
import br.gov.cgu.mbt.negocio.avaliacao.bloco.Bloco;
import br.gov.cgu.mbt.negocio.avaliacao.questao.QuestaoRepository;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MigradorAvaliacaoEbtIntegracaoIT {
	
	/*private EBTRespostaParser ebtQuestionarioParser = 
			new EBTRespostaParser("ebt/ebt_questionario.csv");*/
	
	@Autowired
	private MigradorAvaliacaoEbtService migradorAvaliacaoEbtService;
	
	@Autowired
	private AvaliacaoRepository avaliacaoRepository;
	
	@Autowired
	private QuestaoRepository questaoRepository; // mock
	
	@Test
	public void avaliacoes_migradas_corretamente() throws Exception {
		
		migradorAvaliacaoEbtService.criarAvaliacoesIndependentes();
		
		List<Avaliacao> avaliacoes = avaliacaoRepository.getAll();
		
		
		
		/*List<Questao> questoes = questaoRepository.getAll();
		
		for (Questao questao : questoes) {
			Questao eagerLoaded = questaoRepository.getEagerLoaded(questao.getId());
			assertThat(eagerLoaded.getBloco().getAvaliacao()).isNotNull();
		}*/
		
		for (Avaliacao avaliacao : avaliacoes) {
			Avaliacao avaliacaoEager = avaliacaoRepository.get(avaliacao.getId());
			
			assertThat(avaliacaoEager.getBlocos())
				.isNotEmpty()
				.hasSize(2);
			
			for (Bloco bloco : avaliacaoEager.getBlocos()) {
				assertThat(bloco.getQuestoes())
					.isNotEmpty()
					.hasSize(2);
			}
			
			//assertThat(questaoRepository.getPorAvaliacao(avaliacao)).isNotEmpty();
		}
		
	}
	
	

}
