package br.gov.cgu.projetoexemplosb.aplicacao.configuracao;

import br.gov.cgu.projetoexemplosb.negocio.configuracao.Configuracao;
import br.gov.cgu.projetoexemplosb.negocio.configuracao.ConfiguracaoRepository;
import br.gov.cgu.persistencia.jpa.EntidadeNaoEncontradaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@CacheConfig(cacheNames = "Configuracoes", keyGenerator = "simpleKeyGenerator")
public class ConfiguracoesDoApp {

    private ConfiguracaoRepository repository;

    @Autowired
    public ConfiguracoesDoApp(ConfiguracaoRepository repository) {
        this.repository = repository;
    }

    @Cacheable(key = "#id")
    public String getConfiguracao(String id) {
        try {
            Configuracao configuracao = repository.get(id);
            return configuracao.getValor();
        } catch (EntidadeNaoEncontradaException ignored) {
            return "";
        }
    }

    @CachePut(key = "#id")
    @Transactional
    public String setConfiguracao(String id, String valor) {
        repository.remove(id);
        Configuracao put = repository.put(new Configuracao(id, valor));
        return put.getValor();
    }
}