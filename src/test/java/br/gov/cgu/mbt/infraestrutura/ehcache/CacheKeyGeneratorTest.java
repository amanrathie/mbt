package br.gov.cgu.mbt.infraestrutura.ehcache;


import org.junit.Test;

import br.gov.cgu.mbt.infraestrutura.ehcache.CacheKeyGenerator;

import java.lang.reflect.Method;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class CacheKeyGeneratorTest {

    private CacheKeyGenerator cacheKeyGenerator = new CacheKeyGenerator();

    @Test
    public void generate_com_parametros() throws Exception {
        Method metodo = MeuObjeto.class.getMethod("meuMetodo", String.class);

        String param1 = "param1 preenchido";
        String key = (String) cacheKeyGenerator.generate(new MeuObjeto(), metodo, param1);

        assertThat(key, is("MeuObjeto.meuMetodo#" + param1.hashCode()));
    }

    @Test
    public void generate_deve_atribuir_null_a_parametros_nulos() throws Exception {
        Method metodo = MeuObjeto.class.getMethod("meuMetodo", String.class);
        String key = (String) cacheKeyGenerator.generate(new MeuObjeto(), metodo, (String) null);

        assertThat(key, is("MeuObjeto.meuMetodo#null"));
    }


    private class MeuObjeto {
        public void meuMetodo(String param1) {
        }
    }
}