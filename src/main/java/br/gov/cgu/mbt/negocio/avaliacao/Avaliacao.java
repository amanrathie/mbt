package br.gov.cgu.mbt.negocio.avaliacao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.NotBlank;

import br.gov.cgu.mbt.Constantes;
import br.gov.cgu.mbt.negocio.avaliacao.questionario.Questionario;
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
@Audited
@Entity
@Table(name = "Avaliacao", schema = Constantes.SCHEMA_APLICACAO)
public class Avaliacao implements Entidade<Integer>, Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="IdAvaliacao")
	private Integer id;
	
	@Size(min = 1, max = 64)
	@Column(name="NomAvaliacao")
	@NotBlank(message = "Nome da avaliação é obrigatório")
	private String nome;
	
	@Column(name="NumEdicao")
	private Integer edicao;
	
	@Column(name="IdTipoFaseAvaliacao")
	@Enumerated(EnumType.ORDINAL)
	private TipoFaseAvaliacao fase;
	
	@ManyToOne
	@JoinColumn(name="IdQuestionario")
	private Questionario questionario;
	
}
