package br.gov.cgu.mbt.negocio.entidadeavaliada;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.gov.cgu.mbt.Constantes;

@Entity
@Table(name = "Localidade", schema = Constantes.SCHEMA_APLICACAO)
public class Localidade extends EntidadeAvaliada {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="IdLocalidade")
	private Integer id;

}
