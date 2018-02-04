package br.gov.cgu.mbt.negocio.avaliacao.questao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import br.gov.cgu.mbt.Constantes;
import br.gov.cgu.mbt.negocio.avaliacao.bloco.Bloco;
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
@Table(name = "Questao", schema = Constantes.SCHEMA_APLICACAO)
public class Questao implements Entidade<Integer>, Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="IdQuestao")
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="IdBloco")
	private Bloco bloco;
	
	@Column(name = "IdTipoQuestao")
	@Enumerated(EnumType.ORDINAL)
	private TipoQuestao tipo;
	
	@Column(name = "TxtPergunta")
	private String pergunta;
	
	@Column(name = "ValPeso")
	private BigDecimal peso;
	
	@Column(name = "TxtEstrutura")
	private String estrutura;
	
	@Column(name = "NumOrdem")
	private Integer ordem;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy="questao")
	private List<Resposta> respostas;
	
}
