package br.gov.cgu.mbt.aplicacao.avaliacao.migrador;

import static org.mockito.BDDMockito.given;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import br.gov.cgu.mbt.aplicacao.avaliacao.migrador.builder.AvaliacaoEbtBuilder;
import br.gov.cgu.mbt.negocio.avaliacao.Avaliacao;
import br.gov.cgu.mbt.negocio.avaliacao.AvaliacaoRepository;

@RunWith(SpringRunner.class)
public class MigradorAvaliacaoEbtTest {
	
	@Mock
	private AvaliacaoRepository avaliacaoRepository;
	
	@InjectMocks
	private MigradorAvaliacaoEbtService avaliacaoEbtMigrador; 
	
	/*@Before
	public void setUp() {
		avaliacaoRepository = mock(AvaliacaoRepository.class);
		avaliacaoEbtMigrador = new MigradorAvaliacaoEbtService(avaliacaoRepository);
	}*/
	
	
	@Test
	public void migra_avaliacoes_corretamente() throws Exception {
		List<Avaliacao> avaliacoes = new AvaliacaoEbtBuilder().build();
	
		given(avaliacaoRepository.save(avaliacoes)).willReturn(avaliacoes);
		
		//assertThat(avaliacaoEbtMigrador).isNotNull();
	}
	
	

}
