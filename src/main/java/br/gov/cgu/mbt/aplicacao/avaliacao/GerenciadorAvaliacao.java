package br.gov.cgu.mbt.aplicacao.avaliacao;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.cgu.mbt.negocio.avaliacao.Avaliacao;

@Service
public class GerenciadorAvaliacao {
	
	private AvaliacaoRepository repository;

	@Autowired
	public GerenciadorAvaliacao(AvaliacaoRepository repository) {
		this.repository = repository;
	}
	
	@Transactional
	public Avaliacao salvar(Avaliacao avaliacao) {
		return repository.put(avaliacao);
	}
}
