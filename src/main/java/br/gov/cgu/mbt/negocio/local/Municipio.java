package br.gov.cgu.mbt.negocio.local;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import br.gov.cgu.mbt.Constantes;
import br.gov.cgu.persistencia.jpa.Entidade;

@Entity
@Table(name = "Vw_Municipio", schema = Constantes.SCHEMA_APLICACAO)
@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
public class Municipio implements Entidade<Integer>, Serializable {

    @Id
    @Column(name = "IdMunicipio")
    private Integer id;

    @Column(name = "DescMunicipio")
    private String descricao;

    @Column(name = "SigUF", updatable = false)
    @Enumerated(EnumType.STRING)
    private UF uf;
    
    @Column(name="CodMunicipioGeo")
    private Integer codIBGE;

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
