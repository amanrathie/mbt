package br.gov.cgu.projetoexemplosb.negocio.configuracao;

import br.gov.cgu.projetoexemplosb.Constantes;
import br.gov.cgu.persistencia.jpa.Entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "Configuracao", schema = Constantes.SCHEMA_APLICACAO)
public class Configuracao implements Entidade<String> {

	@Id
	@Column(name = "id", unique = true, nullable = false)
	private String id;

	@Column(name = "valor", nullable = false, length = 3)
	private String valor;

    public Configuracao() { /* Construtor padrão pro JPA */}

    public Configuracao(String id, String valor) {
        this.id = id;
        this.valor = valor;
    }

    @Override
	public String getId() {
		return id;
	}

	public String getValor() {
		return valor;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}
}