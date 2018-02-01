package br.gov.cgu.mbt.aplicacao.avaliacao.migrador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.cgu.mbt.aplicacao.avaliacao.migrador.builder.AvaliacaoEbtBuilder;
import br.gov.cgu.mbt.aplicacao.avaliacao.migrador.builder.BlocoEbtBuilder;
import br.gov.cgu.mbt.aplicacao.avaliacao.migrador.builder.QuestaoEbtBuilder;
import br.gov.cgu.mbt.negocio.avaliacao.Avaliacao;
import br.gov.cgu.mbt.negocio.avaliacao.AvaliacaoRepository;
import br.gov.cgu.mbt.negocio.avaliacao.bloco.Bloco;
import br.gov.cgu.mbt.negocio.avaliacao.questao.Questao;

@Service
public class MigradorAvaliacaoEbtService {
	
	@Autowired
	private AvaliacaoRepository avaliacaoRepository;
	
	@Autowired
	public MigradorAvaliacaoEbtService(AvaliacaoRepository avaliacaoRepository) {
		this.avaliacaoRepository = avaliacaoRepository;
	}
	
	public void migrar() {
		// Inicialmente só teremos as EBT's, então podemos obter todas
		List<Avaliacao> avaliacoes = new AvaliacaoEbtBuilder().build();
		
		for (Avaliacao avaliacao : avaliacoes) {
			List<Bloco> blocos = new BlocoEbtBuilder().build();
			
			for (Bloco bloco : blocos) {
				avaliacao.addBloco(bloco);
				
				List<Questao> questoes = new QuestaoEbtBuilder().build(bloco);
				
				for (Questao questao : questoes) {
					bloco.addQuestao(questao);
				}
			}
			
			avaliacaoRepository.save(avaliacao);
		}
	}
}
