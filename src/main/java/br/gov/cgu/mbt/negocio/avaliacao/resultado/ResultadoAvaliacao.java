package br.gov.cgu.mbt.negocio.avaliacao.resultado;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import br.gov.cgu.mbt.Constantes;
import br.gov.cgu.mbt.negocio.avaliacao.Avaliacao;
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
//@Audited
@Entity
@Table(name = "ResultadoAvaliacao", schema = Constantes.SCHEMA_APLICACAO)
public class ResultadoAvaliacao implements Entidade<Integer>, Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="IdResultadoAvaliacao")
	private Integer id;
	
	@Column(name="NomMunicipio")
	private String nomeMunicipio;
	
	@Column(name="ValNota")
	private BigDecimal nota;
	
	@ManyToOne
	@JoinColumn(name="IdAvaliacao")
	private Avaliacao avaliacao;
	
}
