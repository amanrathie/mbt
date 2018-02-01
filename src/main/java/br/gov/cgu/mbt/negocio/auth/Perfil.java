package br.gov.cgu.mbt.negocio.auth;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.gov.cgu.mbt.Constantes;
import br.gov.cgu.persistencia.jpa.Entidade;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "Perfil", schema = Constantes.SCHEMA_APLICACAO)
public class Perfil implements Entidade<Integer>, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="IdPerfil", unique = true, nullable = false)
    private Integer id;

    @Column(name = "DescPerfil")
    private String nome;
}