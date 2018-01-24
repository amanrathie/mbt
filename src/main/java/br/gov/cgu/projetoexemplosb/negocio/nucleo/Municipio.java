package br.gov.cgu.projetoexemplosb.negocio.nucleo;

import br.gov.cgu.projetoexemplosb.Constantes;
import br.gov.cgu.persistencia.jpa.Entidade;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "Municipio", schema = Constantes.SCHEMA_APLICACAO)
@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
public class Municipio implements Entidade<Integer>, Serializable {

    @Id
    @Column(name = "IdMunicipio")
    private Integer id;

    @NotBlank(message = "Nome do município não pode estar em branco")
    @Column(name = "DescMunicipio")
    private String descricao;

    @NotNull(message = "UF do município é obrigatória")
    @Column(name = "SigUF", updatable = false)
    @Enumerated(EnumType.STRING)
    private UF uf;

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public UF getUf() {
        return uf;
    }

    public void setUf(UF uf) {
        this.uf = uf;
    }

    @Override
    public String toString() {
        return uf.name() + " - " + descricao;
    }
}
