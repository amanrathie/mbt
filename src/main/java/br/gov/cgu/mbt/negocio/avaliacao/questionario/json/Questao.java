package br.gov.cgu.mbt.negocio.avaliacao.questionario.json;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;

import br.gov.cgu.mbt.negocio.avaliacao.questao.Resposta;
import br.gov.cgu.mbt.negocio.avaliacao.questao.TipoQuestao;
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
public class Questao {
	
	private Integer id;
	
	private TipoQuestao tipo;
	
	private String pergunta;
	
	private BigDecimal peso;
	
	private String estrutura;
	
	private Integer ordem;
	
}
