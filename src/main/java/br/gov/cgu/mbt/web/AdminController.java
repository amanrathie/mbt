package br.gov.cgu.mbt.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.gov.cgu.mbt.aplicacao.avaliacao.painelgeral.PainelGeralAvaliacaoFiltro;
import br.gov.cgu.mbt.negocio.TipoPoder;
import br.gov.cgu.mbt.negocio.TipoStatus;
import br.gov.cgu.mbt.negocio.avaliacao.TipoAvaliacao;
import br.gov.cgu.mbt.negocio.avaliacao.TipoFaseAvaliacao;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@GetMapping("/painel_geral_avaliacoes")
	public String painelGeralAvaliacoes(PainelGeralAvaliacaoFiltro filtro, Model m) {
		m.addAttribute("filtro", filtro);
		m.addAttribute("tipos", TipoAvaliacao.values());
		m.addAttribute("fases", TipoFaseAvaliacao.values());
		m.addAttribute("poderes", TipoPoder.values());
		m.addAttribute("status", TipoStatus.values());
		return "/admin/painel_geral_de_avaliacoes" ;
	}
}