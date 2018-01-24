package br.gov.cgu.projetoexemplosb.web.auth;

import br.gov.cgu.projetoexemplosb.aplicacao.auth.BuscadorDePerfil;
import br.gov.cgu.projetoexemplosb.negocio.auth.Perfil;
import br.gov.cgu.persistencia.jpa.EntidadeInvalidaException;
import br.gov.cgu.test.mvc.ControllerTest;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class BuscarPerfilControllerTest extends ControllerTest {
    private BuscadorDePerfil buscador = mock(BuscadorDePerfil.class);
    private BuscarPerfilController controller = new BuscarPerfilController(buscador);

    @Override
    public Object getController() {
        return controller;
    }

    @Test
    public void listarPerfisPublicoExterno__deve_retornar_lista_de_perfis_json() throws Exception {
        when(buscador.recuperarTodosPublicoExterno()).thenReturn(criarPerfis());

        mockMvc.perform(get("/api/auth/perfil/publico-externo"))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"1\":\"Perfil 1\",\"2\":\"Perfil 2\"}"));
    }

    @Test
    public void listarPerfisPublicoExterno__deve_retornar_bad_request() throws Exception {
        when(buscador.recuperarTodosPublicoExterno()).thenThrow(EntidadeInvalidaException.class);

        mockMvc.perform(get("/api/auth/perfil/publico-externo"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void listarPerfisPublicoInterno__deve_retornar_lista_de_perfis_json() throws Exception {
        when(buscador.recuperarTodos()).thenReturn(criarPerfis());

        mockMvc.perform(get("/api/auth/perfis/publico-interno"))
               .andExpect(status().isOk())
               .andExpect(content().string("{\"1\":\"Perfil 1\",\"2\":\"Perfil 2\"}"));
    }

    @Test
    public void listarPerfisPublicoInterno__deve_retornar_bad_request() throws Exception {
        when(buscador.recuperarTodos()).thenThrow(EntidadeInvalidaException.class);

        mockMvc.perform(get("/api/auth/perfis/publico-interno"))
               .andExpect(status().isBadRequest());
    }

    private List<Perfil> criarPerfis() {
        List<Perfil> perfis = new ArrayList<>();
        Perfil perfil = new Perfil();
        perfil.setId(1);
        perfil.setNome("Perfil 1");
        perfis.add(perfil);
        perfil = new Perfil();
        perfil.setId(2);
        perfil.setNome("Perfil 2");
        perfis.add(perfil);

        return perfis;
    }
}