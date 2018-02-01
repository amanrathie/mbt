package br.gov.cgu.mbt.negocio.avaliacao.bloco;

import java.io.Serializable;
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
import javax.persistence.Table;

import br.gov.cgu.mbt.Constantes;
import br.gov.cgu.mbt.negocio.avaliacao.Avaliacao;
import br.gov.cgu.mbt.negocio.avaliacao.questao.Questao;
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
@Table(name = "Bloco", schema = Constantes.SCHEMA_APLICACAO)
public class Bloco implements Entidade<Integer>, Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="IdBloco")
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="IdAvaliacao")
	private Avaliacao avaliacao;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy="bloco")
	private List<Questao> questoes;
	
	@Column(name = "NomBloco")
	private String nome;
	
	@Column(name = "DescBloco")
	private String descricao;
	
	@Column(name = "ValPeso")
	private BigDecimal peso;
	
	@Column(name = "FlgObrigatorio")
	private boolean obrigatorio;
	
	@Column(name = "NumOrdem")
	private Integer ordem;
	
	public void addQuestao(Questao questao) {
		if (questoes == null) {
			questoes = new ArrayList<Questao>();
		}
		questoes.add(questao);
		questao.setBloco(this);
	}
	
	public void removeQuestao(Questao questao) {
		questoes.remove(questao);
		questao.setBloco(null);
	}
	
}
