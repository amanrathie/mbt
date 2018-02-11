package br.gov.cgu.mbt.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.springframework.http.MediaType;

import br.gov.cgu.mbt.aplicacao.avaliacao.painelgeral.BuscadorPainelGeralAvaliacao;
import br.gov.cgu.mbt.aplicacao.avaliacao.painelgeral.PainelGeralAvaliacaoDTO;
import br.gov.cgu.mbt.negocio.TipoPoder;
import br.gov.cgu.mbt.negocio.TipoStatus;
import br.gov.cgu.mbt.negocio.avaliacao.TipoAvaliacao;
import br.gov.cgu.mbt.negocio.avaliacao.TipoFaseAvaliacao;
import br.gov.cgu.persistencia.querybuilder.RespostaConsulta;
import br.gov.cgu.test.mvc.ControllerTest;

public class AdminControllerTest extends ControllerTest {
	
	
	private BuscadorPainelGeralAvaliacao buscador = mock(BuscadorPainelGeralAvaliacao.class);
	private AdminController controller = new AdminController(buscador);
	
	
	
	@Test
	public void painelGeralAvaliacoes_deve_retornar_view() throws Exception {
		mockMvc.perform(get("/admin/painel_geral_avaliacoes"))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("filtro"))
				.andExpect(model().attribute("tipos", TipoAvaliacao.values()))
				.andExpect(model().attribute("fases", TipoFaseAvaliacao.values()))
				.andExpect(model().attribute("poderes", TipoPoder.values()))
				.andExpect(model().attribute("status", TipoStatus.values()))
				.andExpect(view().name("/admin/painel_geral_de_avaliacoes"));
	}
	
	 @Test
	    public void grid_retorna_json() throws Exception {
		 List<PainelGeralAvaliacaoDTO> registros = new ArrayList<>();
		 registros.add(mockDTOs());
		 
		 RespostaConsulta<PainelGeralAvaliacaoDTO> respostaConsulta = new RespostaConsulta<>(registros, 1L);
		 
		 when(buscador.buscar(any())).thenReturn(respostaConsulta);
		 
		 mockMvc.perform(get("/admin/api/auth/painel_geral_avaliacoes"))
		 .andExpect(status().isOk())
		 .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
		 .andExpect(content().string(
				 "{\"draw\":0,\"recordsTotal\":1,\"recordsFiltered\":1,\"data\":[{\"direcaoOrdenacao\":\"ASC\",\"colunaOrdenacao\":\"\",\"tamanhoPagina\":15,\"offset\":0,\"nome\":\"Avaliação de Teste\",\"tipo\":\"CIDADA\"}],\"error\":null}"
						 ));
	 }
	 
	 private PainelGeralAvaliacaoDTO mockDTOs() {
		 return new PainelGeralAvaliacaoDTO("Avaliação de Teste", 1);
	 }


	@Override
	public Object getController() {
		// 
		return controller;
	}

}
