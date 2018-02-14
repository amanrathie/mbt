package br.gov.cgu.mbt.aplicacao.entidadeavaliadora;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.cgu.mbt.negocio.entidadeavaliadora.EntidadeAvaliadora;

@Service
public class BuscadorEntidadeAvaliadora {
	
	private EntidadeAvaliadoraRepository repository;
	
	@Autowired
	public BuscadorEntidadeAvaliadora(EntidadeAvaliadoraRepository repository) {
		this.repository = repository;
	}
	
	public List<EntidadeAvaliadora> buscarPorNome(String nome) {
		return repository.getPorTermo(nome);
	}
}
