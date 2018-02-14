package br.gov.cgu.mbt.negocio.avaliacao;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.hibernate.validator.constraints.NotBlank;

import br.gov.cgu.mbt.Constantes;
import br.gov.cgu.mbt.negocio.TipoPoder;
import br.gov.cgu.mbt.negocio.auth.Usuario;
import br.gov.cgu.mbt.negocio.avaliacao.questionario.Questionario;
import br.gov.cgu.mbt.negocio.avaliacao.questionario.RespostaQuestionario;
import br.gov.cgu.mbt.negocio.avaliacao.resultado.ResultadoAvaliacao;
import br.gov.cgu.mbt.negocio.entidadeavaliadora.EntidadeAvaliadora;
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
@Table(name = "Avaliacao", schema = Constantes.SCHEMA_APLICACAO)
public class Avaliacao implements Entidade<Integer>, Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="IdAvaliacao")
	private Integer id;
	
	@Size(min = 1, max = 255)
	@Column(name="NomAvaliacao")
	@NotBlank(message = "Nome da avaliação é obrigatório")
	private String nome;
	
	@Column(name = "DescAvaliacao")
	@NotAudited
	private String descAvaliacao;
	
	@Column(name="IdTipoAvaliacao")
	@Enumerated(EnumType.ORDINAL)
	@NotAudited
	private TipoAvaliacao tipo;
	
	@Column(name="IdFase")
	@Enumerated(EnumType.ORDINAL)
	private TipoFaseAvaliacao fase;
	
	@Column(name="IdPoder")
	@Enumerated(EnumType.ORDINAL)
	@NotAudited
	private TipoPoder poder;
	
	@ManyToOne
	@JoinColumn(name="IdQuestionario")
	@NotAudited
	private Questionario questionario;
	
	@ManyToOne
	@JoinColumn(name="IdEntidadeAvaliadora")
	@NotAudited
	private EntidadeAvaliadora entidadeAvaliadora;
	
	@ManyToOne
	@JoinColumn(name="IdUsuarioAdministrador")
	private Usuario administrador;
	
	@ManyToOne
	@JoinColumn(name="IdUsuarioCGU")
	private Usuario responsavelCGU;
	
	@OneToMany(mappedBy="avaliacao")
	@NotAudited
	private List<ResultadoAvaliacao> resultados;
	
	@OneToMany(mappedBy="avaliacao")
	private List<RespostaQuestionario> respostas;
	
	@Column(name="NumEdicao")
	@NotAudited
	private Integer edicao;

	@Column(name = "DescEdicao")
	@NotAudited
	private String descEdicao;
	
	@Column(name = "DthInicio")
    private LocalDateTime dataInicio;
	 
	@Column(name = "DthFim")
    private LocalDateTime dataFim;
	
	@Column(name="FlgAtiva")
	private boolean ativo;
	
	// O Lombok reconhece valores padrões para builders dessa maneira
	public static class AvaliacaoBuilder {
		private boolean ativo = true;
	}
	
}
