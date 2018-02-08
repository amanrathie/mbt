package br.gov.cgu.mbt.aplicacao.avaliacao.painelgeral;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.cgu.persistencia.querybuilder.RespostaConsulta;

@Service
public class BuscadorPainelGeralAvaliacao {

    private final PainelGeralAvaliacaoQueryBuilder queryBuilder;

    @Autowired
    public BuscadorPainelGeralAvaliacao(PainelGeralAvaliacaoQueryBuilder queryBuilder) {
        this.queryBuilder = queryBuilder;
    }

    public RespostaConsulta<PainelGeralAvaliacaoDTO> buscar(PainelGeralAvaliacaoFiltro filtro) {
        return queryBuilder.build(filtro);
    }

}
