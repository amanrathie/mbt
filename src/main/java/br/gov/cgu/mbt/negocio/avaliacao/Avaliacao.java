package br.gov.cgu.mbt.negocio.avaliacao;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import br.gov.cgu.mbt.negocio.avaliacao.questionario.Questionario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Avaliacao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="IdAvaliacao")
	private Integer id;
	
	@Size(min = 1, max = 64) // TODO: check se tem que ficar aqui ou no DTO
	@Column(name="nomAvaliacao")
	@NotBlank(message = "Nome da avaliação é obrigatório")
	private String nome;
	
	@OneToOne(mappedBy = "avaliacao", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Questionario questionario;

}
