package br.gov.cgu.projetoexemplosb.negocio.unidade;

import java.util.ArrayList;
import java.util.List;

public class UnidadeForm {

    private Integer id;
    private TipoUnidade tipo;
    private Integer idOrgao;
    private String nome;
    private String sigla;
    private String codigo;
    private Integer idUnidadeSuperior;
    private String email;
    private String telefone;
    private Integer idMunicipio;
    private List<Integer> gestores = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TipoUnidade getTipo() {
        return tipo;
    }

    public void setTipo(TipoUnidade tipo) {
        this.tipo = tipo;
    }

    public Integer getIdOrgao() {
        return idOrgao;
    }

    public void setIdOrgao(Integer idOrgao) {
        this.idOrgao = idOrgao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Integer getIdUnidadeSuperior() {
        return idUnidadeSuperior;
    }

    public void setIdUnidadeSuperior(Integer idUnidadeSuperior) {
        this.idUnidadeSuperior = idUnidadeSuperior;
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

    public Integer getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(Integer idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    public List<Integer> getGestores() {
        return gestores;
    }

    public void setGestores(List<Integer> gestores) {
        this.gestores = gestores;
    }
}