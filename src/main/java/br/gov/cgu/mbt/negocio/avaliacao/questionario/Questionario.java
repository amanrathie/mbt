package br.gov.cgu.mbt.negocio.avaliacao.questionario;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import br.gov.cgu.mbt.negocio.avaliacao.Avaliacao;
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
public class Questionario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="IdQuestionario")
	private Integer id;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="IdAvaliacao")
	private Avaliacao avaliacao;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "questionario")
	private List<Bloco> blocos;
	
	public void addBloco(Bloco bloco) {
		if (blocos == null) {
			blocos = new ArrayList<>();
		}
		blocos.add(bloco);
		bloco.setQuestionario(this);
	}
	
	public void removeBloco(Bloco bloco) {
		blocos.remove(bloco);
		bloco.setQuestionario(null);
	}
}
