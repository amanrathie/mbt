package br.gov.cgu.mbt.aplicacao.auth;

import org.springframework.stereotype.Service;

import com.querydsl.core.types.Expression;
import com.querydsl.jpa.sql.JPASQLQuery;

import br.gov.cgu.mbt.aplicacao.auth.QUsuarioDTO;
import br.gov.cgu.mbt.nucleo.negocio.sqlentities.SLotacoesDoUsuario;
import br.gov.cgu.mbt.nucleo.negocio.sqlentities.SPerfisDoUsuario;
import br.gov.cgu.mbt.nucleo.negocio.sqlentities.SUsuario;
import br.gov.cgu.persistencia.querybuilder.QueryBuilderJPASQL;

@Service
public class UsuarioQueryBuilder extends QueryBuilderJPASQL<UsuarioFiltro, UsuarioDTO> {

    private static final SUsuario usuario = SUsuario.Usuario;
    private static final SPerfisDoUsuario perfis = SPerfisDoUsuario.PerfisDoUsuario;
    private static final SLotacoesDoUsuario lotacoes = SLotacoesDoUsuario.LotacoesDoUsuario;

    @Override
    public JPASQLQuery<UsuarioDTO> gerarQuery(UsuarioFiltro filtro) {
        JPASQLQuery<UsuarioDTO> query = new JPASQLQuery<>(entityManager, sqlTemplate);

        query.select(new QUsuarioDTO(usuario.idUsuario,
        		usuario.nomUsuario,
        		usuario.cpfUsuario,
        		usuario.siapeUsuario,
        		usuario.descLogin,
        		usuario.emlUsuario,
        		usuario.flgAtivo
        ));
        
        query.from(usuario);
        
        filtrarSePreenchido(query, filtro.getNome(), x -> usuario.nomUsuario.contains(filtro.getNome()));
        filtrarSePreenchido(query, filtro.getCpfDesformatado(), x -> usuario.cpfUsuario.equalsIgnoreCase(filtro.getCpfDesformatado()));
        filtrarSePreenchido(query, filtro.getSiape(), x -> usuario.siapeUsuario.equalsIgnoreCase(filtro.getSiape()));
        filtrarSePreenchido(query, filtro.getLogin(), x -> usuario.descLogin.contains(filtro.getLogin()));
        filtrarSeNaoForVazio(query, filtro.getPerfis(), perfis.idPerfil);
        filtrarSeNaoForVazio(query, filtro.getUnidades(), lotacoes.idUnidade);
        filtrarSePreenchido(query, filtro.getAtivo(), x -> usuario.flgAtivo.eq(filtro.getAtivo()));
        
        adicionarJoinSeNecessario(query, perfis, usuario.idUsuario.eq(perfis.idUsuario));
        adicionarJoinSeNecessario(query, lotacoes, usuario.idUsuario.eq(lotacoes.idUsuario));

        return query;
    }

    @Override
    public Expression<? extends Comparable> getOrderByExpression(String coluna) {
        switch (coluna) {
            case "cpf" : return usuario.cpfUsuario;
            case "nome" : return usuario.nomUsuario;
            case "login" : return usuario.descLogin;
            default : return usuario.idUsuario;
        }
    }
}
