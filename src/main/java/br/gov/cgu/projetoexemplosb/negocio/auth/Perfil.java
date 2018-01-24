package br.gov.cgu.projetoexemplosb.negocio.auth;

import br.gov.cgu.projetoexemplosb.Constantes;
import br.gov.cgu.persistencia.jpa.Entidade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Perfil", schema = Constantes.SCHEMA_APLICACAO)
public class Perfil implements Entidade<Integer>, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="IdPerfil", unique = true, nullable = false)
    private Integer id;

    @Column(name = "DescPerfil")
    private String nome;

    @Column(name = "FlgOferecidoPublicoExterno")
    private boolean oferecidoPublicoExterno = false;

    @ElementCollection(targetClass = Permissao.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "PermissoesDoPerfil", schema = Constantes.SCHEMA_APLICACAO, joinColumns = {@JoinColumn(name = "IdPerfil")})
    @Column(name = "IdPermissao")
    @Enumerated(EnumType.STRING)
    private List<Permissao> permissoes = new ArrayList<>();

    public Perfil() {
        /* construtor padrão do hibernate */
    }

    public Perfil(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isOferecidoPublicoExterno() {
        return oferecidoPublicoExterno;
    }

    public void setOferecidoPublicoExterno(boolean oferecidoPublicoExterno) {
        this.oferecidoPublicoExterno = oferecidoPublicoExterno;
    }

    public List<Permissao> getPermissoes() {
        return permissoes;
    }

    public void setPermissoes(List<Permissao> permissoes) {
        this.permissoes = permissoes;
    }

    @Override
    public boolean equals(Object o) {
        return this == o || !(o == null || getClass() != o.getClass()) && id.equals(((Perfil) o).id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}