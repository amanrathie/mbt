package br.gov.cgu.mbt.aplicacao.unidade;

import com.querydsl.core.annotations.QueryProjection;

import br.gov.cgu.mbt.negocio.unidade.TipoUnidade;

public class UnidadeDTO {

    private final Integer id;
    private final String tipo;
    private final String nome;
    private final String sigla;
    private final String codigo;
    private final String uf;
    private final String municipio;
    private final boolean ativo;

    @QueryProjection
    public UnidadeDTO(Integer id, int tipo, String nome, String sigla, String codigo, String uf, String municipio, boolean ativo) {
        this.id = id;
        this.tipo = TipoUnidade.values()[tipo].name();
        this.nome = nome;
        this.sigla = sigla;
        this.codigo = codigo;
        this.uf = uf;
        this.municipio = municipio;
        this.ativo = ativo;
    }

    public Integer getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public String getNome() {
        return nome;
    }

    public String getSigla() {
        return sigla;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getUf() {
        return uf;
    }

    public String getMunicipio() {
        return municipio;
    }

    public boolean isAtivo() {
        return ativo;
    }
}
