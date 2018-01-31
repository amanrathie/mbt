package br.gov.cgu.mbt.aplicacao.avaliacao.migrador;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.gov.cgu.mbt.negocio.avaliacao.AvaliacaoRepository;

@RunWith(SpringRunner.class)
//@DataJpaTest
//@SpringBootTest
public class MigradorAvaliacaoEbtIntegracaoTest {
	
	/*private EBTRespostaParser ebtQuestionarioParser = 
			new EBTRespostaParser("ebt/ebt_questionario.csv");*/
	
	@Autowired
	private MigradorAvaliacaoEbtService migradorAvaliacaoEbtService;
	
	@Autowired
	private AvaliacaoRepository avaliacaoRepository; // mock
	
	@Test
	public void carrega_arquivo_corretamente() throws Exception {
		//assertThat(migradorAvaliacaoEbtService.migrar()).
	}
	
	

}
