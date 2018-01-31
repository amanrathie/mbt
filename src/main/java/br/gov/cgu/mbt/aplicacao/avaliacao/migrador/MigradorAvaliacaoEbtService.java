package br.gov.cgu.mbt.aplicacao.avaliacao.migrador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.cgu.mbt.aplicacao.avaliacao.migrador.builder.AvaliacaoEbtBuilder;
import br.gov.cgu.mbt.negocio.avaliacao.Avaliacao;
import br.gov.cgu.mbt.negocio.avaliacao.AvaliacaoRepository;

@Service
public class MigradorAvaliacaoEbtService {
	
	private AvaliacaoRepository avaliacaoRepository;
	
	@Autowired
	public MigradorAvaliacaoEbtService(AvaliacaoRepository avaliacaoRepository) {
		this.avaliacaoRepository = avaliacaoRepository;
	}
	
	public void migrar() {
		List<Avaliacao> avaliacoes = new AvaliacaoEbtBuilder().build();
		
		avaliacaoRepository.save(avaliacoes);
	}
}
