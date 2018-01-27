package br.gov.cgu.mbt.web.avaliacao.migrador;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class MigradorAvaliacaoIndependenteController {

	@GetMapping("/avaliacao/migrador")
	public String migrarAvaliacoesIndependentes() {
		return "Migrando avaliações independentes...";
	}
	
}
