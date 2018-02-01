package br.gov.cgu.mbt.negocio.log;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;

import br.gov.cgu.mbt.Constantes;

/**
 * A classe Revisao é utilizada pelo Hibernate Envers para adicionar informações aos registros de log no banco de dados.
 * Para cada atributo dessa classe o Envers cria uma coluna no banco de dados.
 *
 * A classe {@link RevisaoListener } é responsável por atribuir o
 * valor dos atributos da classe Revisao.
 */
@Entity
@Table(schema = Constantes.SCHEMA_APLICACAO, name = "Revisao")
@RevisionEntity(RevisaoListener.class)
public class Revisao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @RevisionNumber
    private int id;

    @RevisionTimestamp
    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "dthRevisao")
    private Date dataHora;

    private Integer idUsuario;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Transient
    public Date getRevisionDate() {
        return this.dataHora;
    }

    public Date getDataHora() {
        return dataHora;
    }

    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Revisao revisao = (Revisao) o;

        return id == revisao.id && (dataHora != null ? dataHora.equals(revisao.dataHora) : revisao.dataHora == null);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (dataHora != null ? dataHora.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Revisao(id = " + this.id + ", revisionDate = " + this.dataHora + ")";
    }

    public void setIdUsuario(Integer idUsuario) { this.idUsuario = idUsuario; }

    public Integer getIdUsuario() {
        return idUsuario;
    }
}