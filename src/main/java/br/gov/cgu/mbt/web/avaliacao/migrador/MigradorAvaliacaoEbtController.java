package br.gov.cgu.mbt.web.avaliacao.migrador;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.cgu.mbt.aplicacao.avaliacao.migrador.MigradorAvaliacaoEbtService;

@RestController
@RequestMapping("/admin")
public class MigradorAvaliacaoEbtController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MigradorAvaliacaoEbtController.class);
	
	private MigradorAvaliacaoEbtService migrador;
	
	@Autowired
	public MigradorAvaliacaoEbtController(MigradorAvaliacaoEbtService migrador) {
		this.migrador = migrador;
	}

	@GetMapping("/avaliacao/migracao/criar")
	public String criarAvaliacoesIndependentes() throws Exception {	
		LOGGER.info("Iniciando migração de avaliações EBT's");
		migrador.criarAvaliacoesIndependentes();
		return "Avaliações independentes importadas com sucesso";
	}
	
}
