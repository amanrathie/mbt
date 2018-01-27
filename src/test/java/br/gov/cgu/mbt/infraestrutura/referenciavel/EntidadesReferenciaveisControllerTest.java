package br.gov.cgu.mbt.infraestrutura.referenciavel;

import br.gov.cgu.componentes.pojo.AutocompleteOption;
import br.gov.cgu.mbt.infraestrutura.referenciavel.EntidadesReferenciaveis;
import br.gov.cgu.mbt.infraestrutura.referenciavel.EntidadesReferenciaveisController;
import br.gov.cgu.mbt.negocio.auth.Usuario;
import br.gov.cgu.test.mvc.ControllerTest;
import org.junit.Test;
import org.springframework.http.MediaType;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class EntidadesReferenciaveisControllerTest extends ControllerTest {

    private EntidadesReferenciaveis entidadesReferenciaveis = mock(EntidadesReferenciaveis.class);
    private EntidadesReferenciaveisController controller = new EntidadesReferenciaveisController(entidadesReferenciaveis);

    @Override
    public Object getController() { return controller; }

    @Test
    public void autocomplete__deve_retornar_lista_de_autocompleteoption() throws Exception {
        //given
        String textoBusca = "DTI";
        String clazz = Usuario.class.getName();
        when(entidadesReferenciaveis.getAutocompletePorTermo(clazz, textoBusca)).thenReturn(mockAutocompleteOptions());

        //when // then
        mockMvc.perform(get("/" + clazz + "/autocomplete").param("q", textoBusca))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string("[{\"id\":\"1\",\"name\":\"XXXXXXX\"}]"));
    }

    private List<AutocompleteOption> mockAutocompleteOptions() {
        return Collections.singletonList(mockAutocomplete());
    }

    private AutocompleteOption mockAutocomplete() {
        return new AutocompleteOption("1", "XXXXXXX");
    }

    private List<AutocompleteOption> mockAutocompleteOptionsComAtributos() {
        return Collections.singletonList(mockAutocompleteComAtributos());
    }

    private AutocompleteOption mockAutocompleteComAtributos() {
        Map<String, Object> atributos = new HashMap<>();
        atributos.put("classeCampoPadrao", "RECOMENDACAO");
        return new AutocompleteOption("1", "XXXXXXX");
    }

    @Test
    public void autocompletePorIds__deve_retornar_lista_de_autocompleteoption() throws Exception {
        //given
        List<Integer> ids = asList(1, 2);
        String clazz = Usuario.class.getName();
        when(entidadesReferenciaveis.getAutocompletePorIds(any(), any())).thenReturn(mockAutocompleteOptions());

        //when / then
        mockMvc.perform(get("/" + clazz + "/autocomplete/ids").param("ids", "1,2"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string("[{\"id\":\"1\",\"name\":\"XXXXXXX\"}]"));
    }

}