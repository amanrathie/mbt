package br.gov.cgu.mbt.web.avaliacao.migrador;

import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;

import br.gov.cgu.mbt.aplicacao.avaliacao.migrador.MigradorAvaliacaoEbtService;
import br.gov.cgu.test.mvc.ControllerTest;

public class MigradorAvaliacaoEbtControllerTest extends ControllerTest {
	
	private MigradorAvaliacaoEbtService migrador = mock(MigradorAvaliacaoEbtService.class);
	private MigradorAvaliacaoEbtController controller = new MigradorAvaliacaoEbtController(migrador);

	@Override
	public Object getController() {
		return controller;
	}
	
	@Test
	public void deve_retornar_status_ok_ao_migrar() throws Exception {
		mockMvc.perform(get("/admin/avaliacao/migracao/criar"))
			.andExpect(status().isOk());
	}
}
