package br.gov.cgu.mbt.integracao;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;


public class TestandoIntegracaoIT extends BootIntegracaoTest {
	
	public static String URL_BASE = "http://localhost:8080/";

    @Test
    public void estado_inicial_da_tela() throws Exception {
    	$.url(URL_BASE);
    	
    	assertThat($("h1").html()).isEqualTo("Ola Mapas Brasil");
    }
    
}
