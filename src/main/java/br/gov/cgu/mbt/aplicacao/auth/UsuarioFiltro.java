package br.gov.cgu.mbt.aplicacao.auth;

import br.gov.cgu.persistencia.querybuilder.Filtro;
import br.gov.cgu.utils.CpfCnpjUtils;
import io.swagger.annotations.ApiParam;

import java.util.List;

public class UsuarioFiltro extends Filtro {

    private String nome;
    private String cpf;
    private String siape;
    private String login;
    private Boolean ativo;
	@ApiParam(value = "Lista de ID's dos perfis para filtrar")
    private List<Integer> perfis;
	@ApiParam(value = "Lista de ID's das unidades para filtrar")
    private List<Integer> unidades;

	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCpf() {
		return cpf;
	}
	
	public String getCpfDesformatado() {
		return CpfCnpjUtils.desformatarCpf(getCpf());
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String getSiape() {
		return siape;
	}
	
	public void setSiape(String siape) {
		this.siape = siape;
	}
	
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public Boolean getAtivo() {
		return ativo;
	}
   
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	
    public List<Integer> getPerfis() {
		return perfis;
	}

	public void setPerfis(List<Integer> perfis) {
		this.perfis = perfis;
	}
	
	public List<Integer> getUnidades() {
		return unidades;
	}
	
	public void setUnidades(List<Integer> unidades) {
		this.unidades = unidades;
	}
}
