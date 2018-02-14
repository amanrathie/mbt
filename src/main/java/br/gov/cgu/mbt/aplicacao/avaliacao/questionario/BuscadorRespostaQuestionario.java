package br.gov.cgu.mbt.aplicacao.avaliacao.questionario;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.cgu.mbt.negocio.avaliacao.questionario.RespostaQuestionario;

@Service
public class BuscadorRespostaQuestionario {

	private RespostaQuestionarioRepository repository;

	@Autowired
	public BuscadorRespostaQuestionario(RespostaQuestionarioRepository repository) {
		this.repository = repository;
	}
	
	public List<RespostaQuestionario> getPorIdAvaliacao(Integer idAvaliacao) {
		return repository.getPorIdAvaliacao(idAvaliacao);
	}
}
