package br.gov.cgu.mbt.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.gov.cgu.mbt.negocio.avaliacao.AvaliacaoFiltro;
import br.gov.cgu.mbt.negocio.avaliacao.TipoAvaliacao;
import br.gov.cgu.mbt.negocio.avaliacao.TipoFaseAvaliacao;
import br.gov.cgu.mbt.negocio.avaliacao.TipoPoderAvaliacao;
import br.gov.cgu.mbt.negocio.avaliacao.TipoStatusAvaliacao;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@GetMapping("/painel_geral_avaliacoes")
	public String painelGeralAvaliacoes(AvaliacaoFiltro filtro, Model m) {
		m.addAttribute("filtro", filtro);
		m.addAttribute("tipos", TipoAvaliacao.values());
		m.addAttribute("fases", TipoFaseAvaliacao.values());
		m.addAttribute("poderes", TipoPoderAvaliacao.values());
		m.addAttribute("status", TipoStatusAvaliacao.values());
		return "/admin/painel_geral_de_avaliacoes" ;
	}
}