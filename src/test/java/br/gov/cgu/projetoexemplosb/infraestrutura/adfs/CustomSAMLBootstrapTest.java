package br.gov.cgu.projetoexemplosb.infraestrutura.adfs;

import org.junit.Test;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

import static org.mockito.Mockito.mock;

public class CustomSAMLBootstrapTest {

    private CustomSAMLBootstrap bootstrap = new CustomSAMLBootstrap();

    @Test
    public void name() throws Exception {
        ConfigurableListableBeanFactory beanFactory = mock(ConfigurableListableBeanFactory.class);
        bootstrap.postProcessBeanFactory(beanFactory);
    }
}