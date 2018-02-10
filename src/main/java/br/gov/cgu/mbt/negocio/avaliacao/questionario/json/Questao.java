package br.gov.cgu.mbt.negocio.avaliacao.questionario.json;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import br.gov.cgu.mbt.negocio.avaliacao.questao.TipoQuestao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@JsonTypeInfo(
  use = JsonTypeInfo.Id.NAME, 
  include = JsonTypeInfo.As.PROPERTY, 
  property = "type")
@JsonSubTypes({ 
  @Type(value = QuestaoMultiplaEscolha.class, name = "qme"),
  @Type(value = QuestaoDescritiva.class, name = "qd"),
  @Type(value = QuestaoMatriz.class, name = "qm")
})
public class Questao {
	
	private Integer id;
	
	private TipoQuestao tipo;
	
	private String pergunta;
	
	private BigDecimal peso;
	
	private Integer ordem;
	
	private boolean obrigatoria;
	
}
