package br.gov.cgu.projetoexemplosb.negocio.auth;

import br.gov.cgu.projetoexemplosb.negocio.unidade.QUnidade;
import br.gov.cgu.projetoexemplosb.negocio.unidade.Unidade;
import br.gov.cgu.persistencia.jpa.EntidadeNaoEncontradaException;
import br.gov.cgu.persistencia.jpa.RepositoryJpa;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static br.gov.cgu.persistencia.jpa.JPAUtils.initializeObject;

@Repository
public class UsuarioRepository extends RepositoryJpa<Usuario, Integer> {
    private QUsuario usuario = QUsuario.usuario;
    private QUnidade unidade = QUnidade.unidade;
    private QUnidade unidadeHierarquia = new QUnidade("unidadeHierarquia");

    public Usuario getPorLogin(String login) {
        return getJPAQuery()
                .select(usuario)
                .from(usuario)
                .where(usuario.login.eq(login))
                .fetchOne();
    }

    @Transactional
    public Usuario getPorChaveApi(String chaveApi) {
        Usuario entidade = getJPAQuery()
                .select(this.usuario)
                .from(this.usuario)
                .where(this.usuario.chaveApi.eq(chaveApi))
                .fetchOne();
        if (entidade != null) {
            initializeObject(entidade);
        }
        return entidade;
    }
    
    public Usuario getPorCpf(String cpf) {
        Usuario entidade;
        
        entidade = getJPAQuery()
                    .select(usuario)
                    .from(usuario)
                    .where(usuario.cpf.eq(cpf))
                    .fetchOne();
        
        if (entidade == null) {
            throw new EntidadeNaoEncontradaException(Usuario.class, "CPF = " + cpf);
        }
        
        return entidade;
    }

    public List<Usuario> getPorTermo(String termo) {
        return getJPAQuery()
                .select(usuario)
                .from(usuario)
                .where(
                        usuario.nome.contains(termo)
                                .or(usuario.cpf.eq(termo))
                )
                .limit(10)
                .fetch();
    }

    public List<Usuario> getPorIds(List<Integer> ids) {
        return getJPAQuery()
                .selectFrom(usuario)
                .where(usuario.id.in(ids))
                .fetch();
    }

    public List<Usuario> getPorUnidades(List<Unidade> unidades) {
        return getJPAQuery()
                .select(usuario)
                .from(usuario)
                .innerJoin(usuario.unidades, unidade)
                .where(unidade.in(unidades))
                .where(usuario.ativo.isTrue())
                .fetch();
    }



    public List<Unidade> buscarHierarquiaUnidadesUsuario(Usuario u) {
        return getJPAQuery()
                .select(unidadeHierarquia)
                .from(usuario, unidadeHierarquia)
                .innerJoin(usuario.unidades, unidade)
                .where(usuario.eq(u)
                .and(unidade.hierarquia.endsWith(unidadeHierarquia.hierarquia)))
                .orderBy(unidadeHierarquia.hierarquia.asc())
                .fetch();
    }
}
