package br.gov.cgu.mbt.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;

import br.gov.cgu.test.mvc.ControllerTest;

public class AdminControllerTest extends ControllerTest {

	private AdminController controller = new AdminController();

	@Override
	public Object getController() {
		return controller;
	}

	@Test
	public void painelGeralAvaliacoes_deve_retornar_view() throws Exception {
		mockMvc.perform(get("/admin/painel_geral_avaliacoes"))
				.andExpect(view().name("/admin/painel_geral_de_avaliacoes"));
	}

}
