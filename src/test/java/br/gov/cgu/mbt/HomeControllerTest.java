package br.gov.cgu.mbt;

import br.gov.cgu.mbt.web.HomeController;
import br.gov.cgu.test.mvc.ControllerTest;
import org.junit.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class HomeControllerTest extends ControllerTest {

    private HomeController controller = new HomeController();

    @Override
    public Object getController() {
        return controller;
    }

    @Test
    public void paginaInicial_retorna_a_home_do_portal() throws Exception {
        // given
        // when
        mockMvc.perform(get(""))
               // then
               .andExpect(status().isOk())
               .andExpect(view().name("index/index"));
    }


}