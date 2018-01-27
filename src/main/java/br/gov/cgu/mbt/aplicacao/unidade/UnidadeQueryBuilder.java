package br.gov.cgu.mbt.aplicacao.unidade;

import br.gov.cgu.mbt.aplicacao.unidade.QUnidadeDTO;
import br.gov.cgu.mbt.nucleo.negocio.sqlentities.SMunicipio;
import br.gov.cgu.mbt.nucleo.negocio.sqlentities.SUnidade;
import br.gov.cgu.persistencia.querybuilder.QueryBuilderJPASQL;
import com.querydsl.core.types.Expression;
import com.querydsl.jpa.sql.JPASQLQuery;
import org.springframework.stereotype.Service;

@Service
public class UnidadeQueryBuilder extends QueryBuilderJPASQL<UnidadeFiltro, UnidadeDTO> {

    private SUnidade unidade = SUnidade.Unidade;
    private SMunicipio municipio = SMunicipio.Municipio;

    @Override
    public JPASQLQuery<UnidadeDTO> gerarQuery(UnidadeFiltro filtro) {
        JPASQLQuery<UnidadeDTO> query = new JPASQLQuery<>(entityManager, sqlTemplate);

        query.select(new QUnidadeDTO(
                unidade.idUnidade,
                unidade.idTipoUnidade,
                unidade.nomUnidade,
                unidade.sigUnidade,
                unidade.codUnidade,
                municipio.sigUF,
                municipio.descMunicipio,
                unidade.flgAtivo
        ));
        query.from(unidade)
                .join(municipio).on(municipio.idMunicipio.eq(unidade.idMunicipio));

        filtrarSePreenchido(query, filtro.getNome(), x -> unidade.nomUnidade.contains(filtro.getNome()));
        filtrarSePreenchido(query, filtro.getTipo(), x -> unidade.idTipoUnidade.eq(filtro.getTipo().ordinal()));
        filtrarSePreenchido(query, filtro.getCodigo(), x -> unidade.codUnidade.eq(filtro.getCodigo()));
        filtrarSePreenchido(query, filtro.getSigla(), x -> unidade.sigUnidade.contains(filtro.getSigla()));
        filtrarSePreenchido(query, filtro.getUf(), x -> municipio.sigUF.contains(filtro.getUf()));
        filtrarSeNaoForVazio(query, filtro.getIdsMunicipio(), x -> unidade.idMunicipio.in(filtro.getIdsMunicipio()));
        filtrarSePreenchido(query, filtro.getAtivo(), x -> unidade.flgAtivo.eq(filtro.getAtivo()));

        return query;
    }

    @Override
    public Expression<? extends Comparable> getOrderByExpression(String coluna) {
        switch (coluna) {
            case "tipo" : return unidade.idTipoUnidade;
            case "nome" : return unidade.nomUnidade;
            case "sigla" : return unidade.sigUnidade;
            case "codigo" : return unidade.codUnidade;
            default : return unidade.idUnidade;
        }
    }
}
