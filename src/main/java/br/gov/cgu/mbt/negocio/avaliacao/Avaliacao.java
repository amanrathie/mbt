package br.gov.cgu.mbt.negocio.avaliacao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import br.gov.cgu.mbt.negocio.avaliacao.bloco.Bloco;
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
public class Avaliacao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="IdAvaliacao")
	private Integer id;
	
	@Size(min = 1, max = 64) // TODO: check se tem que ficar aqui ou no DTO
	@Column(name="nomAvaliacao")
	@NotBlank(message = "Nome da avaliação é obrigatório")
	private String nome;
	
	@Column(name="numEdicao")
	private Integer numEdicao;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy="avaliacao")
	private List<Bloco> blocos;
	
	public void addBloco(Bloco bloco) {
		if (blocos == null) {
			blocos = new ArrayList<Bloco>();
		}
		blocos.add(bloco);
		bloco.setAvaliacao(this);
	}
	
	public void removeBloco(Bloco bloco) {
		blocos.remove(bloco);
	}
}