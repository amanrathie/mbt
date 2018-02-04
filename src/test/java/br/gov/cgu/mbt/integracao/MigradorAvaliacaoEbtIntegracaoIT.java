package br.gov.cgu.mbt.integracao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.gov.cgu.mbt.aplicacao.avaliacao.migrador.MigradorAvaliacaoEbtService;
import br.gov.cgu.mbt.negocio.avaliacao.Avaliacao;
import br.gov.cgu.mbt.negocio.avaliacao.AvaliacaoRepository;
import br.gov.cgu.mbt.negocio.avaliacao.TipoFaseAvaliacao;
import br.gov.cgu.mbt.negocio.avaliacao.bloco.Bloco;
import br.gov.cgu.mbt.negocio.avaliacao.bloco.BlocoRepository;

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
	private BlocoRepository blocoRepository;
	
	@Test
	public void avaliacoes_migradas_corretamente() throws Exception {
		
		migradorAvaliacaoEbtService.criarAvaliacoesIndependentes();
		
		List<Avaliacao> avaliacoes = avaliacaoRepository.getAll();
		
		for (Avaliacao avaliacao : avaliacoes) {
			Avaliacao avaliacaoEager = avaliacaoRepository.getEagerLoaded(avaliacao.getId());
			
			assertThat(avaliacao.getFase()).isEqualTo(TipoFaseAvaliacao.PUBLICADA);
			
			assertThat(avaliacaoEager.getBlocos())
				.isNotEmpty()
				.hasSize(2);
			
			List<Bloco> blocos = avaliacaoEager.getBlocos();
			for (Bloco bloco : blocos) {
				Bloco eagerBloco = blocoRepository.getEagerLoaded(bloco.getId());
				
				assertThat(eagerBloco.getQuestoes())
					.isNotEmpty()
					.hasSize(2);
			}
			
			//assertThat(questaoRepository.getPorAvaliacao(avaliacao)).isNotEmpty();
		}
		
	}
	
	

}
