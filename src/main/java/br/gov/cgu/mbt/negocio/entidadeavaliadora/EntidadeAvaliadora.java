package br.gov.cgu.mbt.negocio.entidadeavaliadora;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.gov.cgu.mbt.Constantes;
import br.gov.cgu.mbt.negocio.auth.Usuario;
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
@Entity
@Table(name = "EntidadeAvaliadora", schema = Constantes.SCHEMA_APLICACAO)
public class EntidadeAvaliadora implements Entidade<Integer>, Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="IdEntidadeAvaliadora")
	private Integer id;
	
	@Column(name="NomEntidade")
	private String nome;
	
	@Column(name="FlgCgu")
	private boolean cgu;
	
	@OneToMany(mappedBy="entidadeAvaliadora")
	private List<Avaliacao> avaliacoes;
	
	@OneToMany
	@JoinTable(name = "EntidadeAvaliadora_Usuario", schema = Constantes.SCHEMA_APLICACAO,
		joinColumns = @JoinColumn(name="IdEntidadeAvaliadora"), 
		inverseJoinColumns = @JoinColumn(name="IdUsuario"))
	public List<Usuario> usuarios;

}
