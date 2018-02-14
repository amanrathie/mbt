package br.gov.cgu.mbt.negocio.entidadeavaliada;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import br.gov.cgu.mbt.Constantes;
import br.gov.cgu.mbt.negocio.TipoPoder;
import br.gov.cgu.mbt.negocio.entidade.TipoEntidade;
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
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "EntidadeAvaliada", schema = Constantes.SCHEMA_APLICACAO)
public class EntidadeAvaliada implements Entidade<Integer>, Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="IdEntidadeAvaliada")
	private Integer id;
	
	@Column(name="IdTipoEntidade")
	@Enumerated(EnumType.ORDINAL)
	private TipoEntidade tipo;
	
	@Column(name="IdPoder")
	@Enumerated(EnumType.ORDINAL)
	private TipoPoder poder;

}
