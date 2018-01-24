package br.gov.cgu.projetoexemplosb.negocio.unidade;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class TipoOrgaoTest {

    @Test
    public void toString__deve_retornar_nome() throws Exception {
        for (TipoOrgao tipoOrgao : TipoOrgao.values()) {
            assertThat(tipoOrgao.toString(), is(tipoOrgao.getNome()));
        }
    }
}