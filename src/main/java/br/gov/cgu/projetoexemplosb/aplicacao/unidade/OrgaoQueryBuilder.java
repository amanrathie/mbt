package br.gov.cgu.projetoexemplosb.aplicacao.unidade;

import br.gov.cgu.projetoexemplosb.nucleo.negocio.sqlentities.SOrgao;
import br.gov.cgu.persistencia.querybuilder.QueryBuilderJPASQL;
import com.querydsl.core.types.Expression;
import com.querydsl.jpa.sql.JPASQLQuery;
import org.springframework.stereotype.Service;

@Service
public class OrgaoQueryBuilder extends QueryBuilderJPASQL<OrgaoFiltro, OrgaoDTO> {

    private SOrgao orgao = SOrgao.Orgao;

    @Override
    public JPASQLQuery<OrgaoDTO> gerarQuery(OrgaoFiltro filtro) {
        JPASQLQuery<OrgaoDTO> query = new JPASQLQuery<>(entityManager, sqlTemplate);

        query.select(new QOrgaoDTO(
                orgao.idOrgao,
                orgao.idTipoOrgao,
                orgao.nomOrgao,
                orgao.sigOrgao,
                orgao.codOrgao,
                orgao.flgAtivo
        ));
        query.from(orgao);

        filtrarSePreenchido(query, filtro.getNome(), x -> orgao.nomOrgao.contains(filtro.getNome()));
        filtrarSePreenchido(query, filtro.getTipo(), x -> orgao.idTipoOrgao.eq(filtro.getTipo().ordinal()));
        filtrarSePreenchido(query, filtro.getCodigo(), x -> orgao.codOrgao.eq(filtro.getCodigo()));
        filtrarSePreenchido(query, filtro.getSigla(), x -> orgao.sigOrgao.contains(filtro.getSigla()));
        filtrarSePreenchido(query, filtro.getAtivo(), x -> orgao.flgAtivo.eq(filtro.getAtivo()));

        return query;
    }

    @Override
    public Expression<? extends Comparable> getOrderByExpression(String coluna) {
        switch (coluna) {
            case "tipo" : return orgao.idTipoOrgao;
            case "nome" : return orgao.nomOrgao;
            case "sigla" : return orgao.sigOrgao;
            case "codigo" : return orgao.codOrgao;
            default : return orgao.idOrgao;
        }
    }
}
