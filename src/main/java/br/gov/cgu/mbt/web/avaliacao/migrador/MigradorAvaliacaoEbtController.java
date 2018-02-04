package br.gov.cgu.mbt.web.avaliacao.migrador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.cgu.mbt.aplicacao.avaliacao.migrador.MigradorAvaliacaoEbtService;

@RestController
@RequestMapping("/admin")
public class MigradorAvaliacaoEbtController {
	
	private MigradorAvaliacaoEbtService migrador;
	
	@Autowired
	public MigradorAvaliacaoEbtController(MigradorAvaliacaoEbtService migrador) {
		this.migrador = migrador;
	}

	@GetMapping("/avaliacao/migracao/criar")
	public String criarAvaliacoesIndependentes() throws Exception {		
		migrador.criarAvaliacoesIndependentes();
		return "Formulários EBT's criados com sucesso...";
	}
	
	@GetMapping("/avaliacao/migracao/migrar")
	public String migrarAvaliacoesIndependentes() throws Exception {		
		migrador.migrarAvaliacoesIndependentes();
		return "Migração das respostas EBT's realizada com sucesso";
	}
	
}
