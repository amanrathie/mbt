package br.gov.cgu.mbt.web.datatables;

import br.gov.cgu.componentes.datatables.DataTablesResponse;
import br.gov.cgu.persistencia.querybuilder.RespostaConsulta;

public final class DataTablesResponseFactory {

    private DataTablesResponseFactory() {/* Hide do construtor de casse utilitária */ }

    public static <R> DataTablesResponse<R> build(RespostaConsulta<R> resposta) {
        DataTablesResponse<R> dataTablesResponse = new DataTablesResponse<>();
        dataTablesResponse.setRecordsTotal(resposta.getTotalRegistros());
        dataTablesResponse.setRecordsFiltered(resposta.getTotalRegistros());
        dataTablesResponse.setData(resposta.getRegistros());
        return dataTablesResponse;
    }
}
