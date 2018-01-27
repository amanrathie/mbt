package br.gov.cgu.mbt.aplicacao.unidade;

import br.gov.cgu.mbt.negocio.unidade.TipoOrgao;
import br.gov.cgu.persistencia.querybuilder.Filtro;

public class OrgaoFiltro extends Filtro {

    private String nome;
    private TipoOrgao tipo;
    private String codigo;
    private String sigla;
    private Boolean ativo;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TipoOrgao getTipo() {
        return tipo;
    }

    public void setTipo(TipoOrgao tipo) {
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

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
}
