package br.gov.cgu.mbt.negocio.avaliacao.bloco;

import java.math.BigDecimal;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import br.gov.cgu.mbt.negocio.avaliacao.questao.Questao;
import br.gov.cgu.mbt.negocio.avaliacao.questionario.Questionario;
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
public class Bloco {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="IdBloco")
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="IdQuestionario")
	private Questionario questionario;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy="bloco")
	private List<Questao> questoes;
	
	@Column(name = "nomBloco")
	private String nome;
	
	@Column(name = "descBloco")
	private String descricao;
	
	@Column(name = "valPeso")
	private BigDecimal peso;
	
	@Column(name = "flgObrigatorio")
	private boolean obrigatorio;
	
	@Column(name = "numOrdem")
	private Integer ordem;
	
	public void addQuestao(Questao questao) {
		if (questoes == null) {
			questoes = new ArrayList<>();
		}
		questoes.add(questao);
		questao.setBloco(this);
	}
	
	public void removeQuestao(Questao questao) {
		questoes.remove(questao);
		questao.setBloco(null);
	}
	
}
