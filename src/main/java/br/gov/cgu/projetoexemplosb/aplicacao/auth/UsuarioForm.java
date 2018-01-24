package br.gov.cgu.projetoexemplosb.aplicacao.auth;

import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

public class UsuarioForm {

    @ApiModelProperty(value = "Informe um id caso seja edição de usuário. Não informe nada caso seja criação de novos usuários.")
    private Integer id;
    private String nome;
    private String cpf;
    private String login;
    private String siape;
    private String email;
    private String telefone;
    @ApiModelProperty(value = "Lista de ID's dos perfis")
    private List<Integer> perfis = new ArrayList<>();
    @ApiModelProperty(value = "Lista de ID's das unidades")
    private List<Integer> unidades = new ArrayList<>();

    public UsuarioForm() {/*Construtor padrão pro spring*/}

    public UsuarioForm(UsuarioCaduForm usuarioCaduForm) {
        this.nome = usuarioCaduForm.getNome();
        this.cpf = usuarioCaduForm.getCpf();
        this.login = usuarioCaduForm.getLogin();
        this.email = usuarioCaduForm.getEmail();
        this.telefone = usuarioCaduForm.getTelefone();
        this.perfis = usuarioCaduForm.getPerfis();
        this.unidades.add(usuarioCaduForm.getIdUgLotacao());
    }

    public UsuarioForm(UsuarioAcessoForm usuarioAcessoForm) {
        this.nome = usuarioAcessoForm.getNome();
        this.cpf = usuarioAcessoForm.getCpf();
        this.login = usuarioAcessoForm.getLogin();
        this.email = usuarioAcessoForm.getEmail();
        this.telefone = usuarioAcessoForm.getTelefone();
        this.perfis = usuarioAcessoForm.getPerfis();
        this.unidades.add(usuarioAcessoForm.getIdUnidadeLotacao());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSiape() {
        return siape;
    }

    public void setSiape(String siape) {
        this.siape = siape;
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

    public List<Integer> getUnidades() {
        return unidades;
    }

    public void setUnidades(List<Integer> unidades) {
        this.unidades = unidades;
    }

    public List<Integer> getPerfis() {
        return perfis;
    }

    public void setPerfis(List<Integer> perfis) {
        this.perfis = perfis;
    }
}
