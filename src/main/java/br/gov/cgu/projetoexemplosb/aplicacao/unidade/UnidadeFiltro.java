package br.gov.cgu.projetoexemplosb.aplicacao.unidade;

import br.gov.cgu.projetoexemplosb.negocio.unidade.TipoUnidade;
import br.gov.cgu.persistencia.querybuilder.Filtro;
import io.swagger.annotations.ApiParam;

import java.util.List;

public class UnidadeFiltro extends Filtro {

    private String nome;
    private TipoUnidade tipo;
    private String codigo;
    private String sigla;
    private String uf;
    @ApiParam(value = "Lista de ID's dos municípios para filtrar")
    private List<Integer> idsMunicipio;
    private Boolean ativo;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TipoUnidade getTipo() {
        return tipo;
    }

    public void setTipo(TipoUnidade tipo) {
        this.tipo = tipo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public List<Integer> getIdsMunicipio() {
        return idsMunicipio;
    }

    public void setIdsMunicipio(List<Integer> idsMunicipio) {
        this.idsMunicipio = idsMunicipio;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
}
