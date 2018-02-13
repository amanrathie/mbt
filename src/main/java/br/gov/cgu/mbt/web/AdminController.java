package br.gov.cgu.mbt.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.gov.cgu.componentes.datatables.DataTablesResponse;
import br.gov.cgu.mbt.aplicacao.avaliacao.painelgeral.BuscadorPainelGeralAvaliacao;
import br.gov.cgu.mbt.aplicacao.avaliacao.painelgeral.PainelGeralAvaliacaoDTO;
import br.gov.cgu.mbt.aplicacao.avaliacao.painelgeral.PainelGeralAvaliacaoFiltro;
import br.gov.cgu.mbt.negocio.TipoPoder;
import br.gov.cgu.mbt.negocio.TipoStatus;
import br.gov.cgu.mbt.negocio.avaliacao.TipoAvaliacao;
import br.gov.cgu.mbt.negocio.avaliacao.TipoFaseAvaliacao;
import br.gov.cgu.mbt.web.datatables.DataTablesResponseFactory;
import br.gov.cgu.persistencia.querybuilder.RespostaConsulta;

@Controller
@RequestMapping("/admin")
public class AdminController {

	private BuscadorPainelGeralAvaliacao buscador;

	@Autowired
	public AdminController(BuscadorPainelGeralAvaliacao buscador) {
		this.buscador = buscador;
	}

	@GetMapping("/painel_geral_avaliacoes")
	public String listar(PainelGeralAvaliacaoFiltro filtro, Model m) {		
		
		m.addAttribute("filtro", filtro);
		m.addAttribute("tipos", TipoAvaliacao.values());
		m.addAttribute("fases", TipoFaseAvaliacao.values());
		m.addAttribute("poderes", TipoPoder.values());
		m.addAttribute("status", TipoStatus.values());
		return "/admin/painel_geral_de_avaliacoes";
	}

//	@RequestMapping(value = "/api/auth/painel_geral_avaliacoes", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//	@ResponseBody
//	public DataTablesResponse<PainelGeralAvaliacaoDTO> exibeAvaliacoes(PainelGeralAvaliacaoFiltro filtro) {		
//		RespostaConsulta<PainelGeralAvaliacaoDTO> resposta = buscador.buscar(filtro);
//		return DataTablesResponseFactory.build(resposta);
//	}
	
	@RequestMapping(value = "/api/auth/painel_geral_avaliacoes", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public DataTablesResponse<PainelGeralAvaliacaoDTO> exibeAvaliacoes(Integer numPagina, PainelGeralAvaliacaoFiltro filtro) {
		//Tamanho da pagina 15 por default
		if(numPagina != null) {
			filtro.setOffset(filtro.getTamanhoPagina() * (numPagina -1));
		}
		
		RespostaConsulta<PainelGeralAvaliacaoDTO> resposta = buscador.buscar(filtro);
		return DataTablesResponseFactory.build(resposta);
	}
	
}