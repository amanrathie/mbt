package br.gov.cgu.mbt.aplicacao.auth;

import java.util.ArrayList;
import java.util.List;

public class UsuarioCaduForm {

    private Integer id;
    private String login;
    private String cargo;
    private String email;
    private String cpf;
    private String nome;
    private String telefone;
    private String codUciLotacao;
    private String descUciLotacao;
    private Integer idUgLotacao;
    private String descUgLotacao;
    private List<Integer> perfis = new ArrayList<>();
    private boolean ativo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCodUciLotacao() {
        return codUciLotacao;
    }

    public void setCodUciLotacao(String codUciLotacao) {
        this.codUciLotacao = codUciLotacao;
    }

    public String getDescUciLotacao() {
        return descUciLotacao;
    }

    public void setDescUciLotacao(String descUciLotacao) {
        this.descUciLotacao = descUciLotacao;
    }

    public Integer getIdUgLotacao() {
        return idUgLotacao;
    }

    public void setIdUgLotacao(Integer idUgLotacao) {
        this.idUgLotacao = idUgLotacao;
    }

    public String getDescUgLotacao() {
        return descUgLotacao;
    }

    public void setDescUgLotacao(String descUgLotacao) {
        this.descUgLotacao = descUgLotacao;
    }

    public List<Integer> getPerfis() {
        return perfis;
    }

    public void setPerfis(List<Integer> perfis) {
        this.perfis = perfis;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}
