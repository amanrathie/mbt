package br.gov.cgu.mbt.web.avaliacao.migrador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.cgu.mbt.negocio.avaliacao.Avaliacao;
import br.gov.cgu.mbt.negocio.avaliacao.AvaliacaoRepository;

@RestController
@RequestMapping("/admin")
public class MigradorAvaliacaoIndependenteController {
	
	@Autowired
	private AvaliacaoRepository avaliacaoRepository;

	@GetMapping("/avaliacao/migrador")
	public String migrarAvaliacoesIndependentes() {
		// test
		Avaliacao avaliacao = Avaliacao.builder().nome("Teste de avaliacao").build();
		
		avaliacaoRepository.save(avaliacao);
		
		return "Migrando avaliações independentes...";
	}
	
}
