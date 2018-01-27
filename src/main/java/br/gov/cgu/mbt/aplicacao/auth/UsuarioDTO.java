package br.gov.cgu.mbt.aplicacao.auth;

import com.querydsl.core.annotations.QueryProjection;

import br.gov.cgu.utils.CpfCnpjUtils;

public class UsuarioDTO {

    private Integer id;
    private String nome;
    private String cpf;
    private String siape;
    private String login;
    private String email;
    private boolean ativo;

    @QueryProjection
    public UsuarioDTO(Integer id, String nome, String cpf, String siape, String login, String email, boolean ativo) {
        this.id = id;
        this.nome = nome;
        this.cpf = CpfCnpjUtils.formatarCpf(cpf);
        this.siape = siape;
        this.login = login;
        this.email = email;
        this.ativo = ativo;
    }

	public UsuarioDTO() {
    	//TODO: anselmo
    	//Remover esse construtor depois que remover UsuarioDTO do usuarioRepository
	}

	public Integer getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getCpf() {
		return cpf ;
	}

	public String getSiape() {
		return siape;
	}

	public String getLogin() {
		return login;
	}

	public String getEmail() {
		return email;
	}
	
	public boolean isAtivo() {
		return ativo;
	}
}
