package br.gov.cgu.mbt.negocio.avaliacao.questao;

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
public class Questao {
	
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
	
	@Column(name = "txtPergunta")
	private String pergunta;
	
}
