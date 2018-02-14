package br.gov.cgu.mbt.aplicacao.avaliacao.resultado;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.cgu.mbt.negocio.avaliacao.resultado.ResultadoAvaliacao;

@Service
public class GerenciadorResultadoAvaliacao {

	private ResultadoAvaliacaoRepository repository;

	@Autowired
	public GerenciadorResultadoAvaliacao(ResultadoAvaliacaoRepository repository) {
		this.repository = repository;
	}
	
	@Transactional
	public ResultadoAvaliacao salvar(ResultadoAvaliacao resultado) {
		return repository.put(resultado);
	}
}
