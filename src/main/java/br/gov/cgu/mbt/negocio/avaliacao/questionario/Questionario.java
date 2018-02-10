package br.gov.cgu.mbt.negocio.avaliacao.questionario;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
@Audited
@Entity
@Table(name = "Questionario", schema = Constantes.SCHEMA_APLICACAO)
public class Questionario implements Entidade<Integer>, Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="IdQuestionario")
	private Integer id;
	
	@Column(name = "TxtEstrutura")
	private String estrutura;
	
	@OneToMany(mappedBy="questionario", fetch = FetchType.LAZY)
	private List<Avaliacao> avaliacoes;
	
	@OneToMany(mappedBy="questionario",
			fetch = FetchType.LAZY,
			cascade = CascadeType.ALL, 
			orphanRemoval = true)
	private List<RespostaQuestionario> respostas;
	
	public void addAvaliacao(Avaliacao avaliacao) {
		if (avaliacoes == null) {
			avaliacoes = new ArrayList<Avaliacao>();
		}
		
		avaliacoes.add(avaliacao);
		avaliacao.setQuestionario(this);
	}
	
	public void removeAvaliacao(Avaliacao avaliacao) {
		avaliacoes.remove(avaliacao);
		avaliacao.setQuestionario(null);
	}
	
	public void addResposta(RespostaQuestionario resposta) {
		if (respostas == null) {
			respostas = new ArrayList<RespostaQuestionario>();
		}
		
		respostas.add(resposta);
		resposta.setQuestionario(this);
	}
	
	public void removeAvaliacao(RespostaQuestionario resposta) {
		respostas.remove(resposta);
		resposta.setQuestionario(null);
	}
	
}
