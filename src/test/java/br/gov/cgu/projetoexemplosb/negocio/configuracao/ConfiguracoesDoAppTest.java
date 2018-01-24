package br.gov.cgu.projetoexemplosb.negocio.configuracao;

import br.gov.cgu.projetoexemplosb.aplicacao.configuracao.ConfiguracoesDoApp;
import br.gov.cgu.persistencia.jpa.EntidadeNaoEncontradaException;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class ConfiguracoesDoAppTest {

    private ConfiguracaoRepository repository = mock(ConfiguracaoRepository.class);
    private ConfiguracoesDoApp configuracoes = new ConfiguracoesDoApp(repository);

    @Test
    public void getConfiguracao__retorna_valor_da_Config() throws Exception {
        String id = "Config1";
        String retornoEsperado = "teste";
        Configuracao configuracao = new Configuracao();
        configuracao.setId(id);
        configuracao.setValor(retornoEsperado);

        when(repository.get(id)).thenReturn(configuracao);

        String valor = configuracoes.getConfiguracao(id);

        assertThat(valor, is(retornoEsperado));
    }

    @Test
    public void getConfiguracao__retorna_vazio_se_nao_encontrar_por_id() throws Exception {
        when(repository.get(any())).thenThrow(new EntidadeNaoEncontradaException(Configuracao.class));

        String valor = configuracoes.getConfiguracao("lala");

        assertThat(valor, is(""));
    }

    @Test
    public void setConfiguracao__remove_antiga_e_salva_nova_e_retorna_valor_salvo() throws Exception {
        String valor = "valorlele";
        String id = "idlala";

        Configuracao configuracao = new Configuracao();
        configuracao.setId(id);
        configuracao.setValor(valor);

        when(repository.put(any())).thenReturn(configuracao);

        String valorSalvo = configuracoes.setConfiguracao(id, valor);

        verify(repository).remove(id);
        assertThat(valorSalvo, is(valor));
    }
}