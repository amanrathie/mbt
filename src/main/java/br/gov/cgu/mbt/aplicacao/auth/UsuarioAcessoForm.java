package br.gov.cgu.mbt.aplicacao.auth;

import java.util.List;

public class UsuarioAcessoForm {
    private String cpf;
    private String nome;
    private String login;
    private Integer idUnidadeLotacao;
    private String email;
    private String telefone;
    private List<String> perfil;
    private List<Integer> perfis;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Integer getIdUnidadeLotacao() {
        return idUnidadeLotacao;
    }

    public void setIdUnidadeLotacao(Integer idUnidadeLotacao) {
        this.idUnidadeLotacao = idUnidadeLotacao;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public List<String> getPerfil() {
        return perfil;
    }

    public void setPerfil(List<String> perfil) {
        this.perfil = perfil;
    }

    public List<Integer> getPerfis() {
        return perfis;
    }

    public void setPerfis(List<Integer> perfis) {
        this.perfis = perfis;
    }
}