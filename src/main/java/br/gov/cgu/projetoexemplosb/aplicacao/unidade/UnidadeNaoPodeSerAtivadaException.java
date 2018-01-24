package br.gov.cgu.projetoexemplosb.aplicacao.unidade;

import br.gov.cgu.projetoexemplosb.negocio.unidade.Unidade;

public class UnidadeNaoPodeSerAtivadaException extends RuntimeException {
    public UnidadeNaoPodeSerAtivadaException(Unidade unidade) {
        super("A unidade " + unidade.getSigla() + " não pode ser ativada com sua unidade superior(" +
                unidade.getUnidadeSuperior().getSigla() + ") inativa.");
    }
}
