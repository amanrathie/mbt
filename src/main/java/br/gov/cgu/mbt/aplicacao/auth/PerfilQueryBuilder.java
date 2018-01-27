package br.gov.cgu.mbt.aplicacao.auth;

import br.gov.cgu.mbt.nucleo.negocio.sqlentities.SPerfil;
import br.gov.cgu.persistencia.querybuilder.QueryBuilderJPASQL;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.sql.JPASQLQuery;
import org.springframework.stereotype.Service;

@Service
public class PerfilQueryBuilder extends QueryBuilderJPASQL<PerfilFiltro, PerfilDTO> {

    private SPerfil perfil = SPerfil.Perfil;

    @Override
    public JPASQLQuery<PerfilDTO> gerarQuery(PerfilFiltro filtro) {
        JPASQLQuery<PerfilDTO> query = new JPASQLQuery<>(entityManager, sqlTemplate);

        query.select(Projections.constructor(PerfilDTO.class,
                perfil.idPerfil,
                perfil.descPerfil,
                perfil.flgOferecidoPublicoExterno
        ));

        query.from(perfil);
        return query;
    }

    @Override
    public Expression<? extends Comparable> getOrderByExpression(String coluna) {
        return perfil.descPerfil;
    }
}
