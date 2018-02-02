package br.gov.cgu.mbt.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@GetMapping("/painel_geral_avaliacoes")
	public ModelAndView painelGeralAvaliacoes() {
		return new ModelAndView("/admin/painel_geral_de_avaliacoes");
	}
}