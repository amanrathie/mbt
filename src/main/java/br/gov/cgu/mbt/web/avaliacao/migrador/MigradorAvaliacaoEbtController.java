package br.gov.cgu.mbt.web.avaliacao.migrador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.cgu.mbt.aplicacao.avaliacao.migrador.MigradorAvaliacaoEbtService;

@RestController
@RequestMapping("/admin")
public class MigradorAvaliacaoEbtController {
	
	@Autowired
	private MigradorAvaliacaoEbtService migrador;

	@GetMapping("/avaliacao/migrador")
	public String migrarAvaliacoesIndependentes() {		
		migrador.migrar();
		return "Migrando avaliações independentes...";
	}
	
}
