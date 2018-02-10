package br.gov.cgu.mbt.negocio.avaliacao.questionario;

import java.io.Serializable;

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
@Table(name = "Resposta", schema = Constantes.SCHEMA_APLICACAO)
public class RespostaQuestionario implements Entidade<Integer>, Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="IdResposta")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="IdQuestionario")
	private Questionario questionario;
	
	@Column(name = "TxtEstrutura")
	private String estrutura;
	
	@Column(name = "municipio") // temporario
	private String municipio;

}
