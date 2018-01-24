package br.gov.cgu.projetoexemplosb.aplicacao.unidade;

import br.gov.cgu.projetoexemplosb.negocio.unidade.TipoOrgao;
import com.querydsl.core.annotations.QueryProjection;

public class OrgaoDTO {

    private final Integer id;
    private final String tipo;
    private final String nome;
    private final String sigla;
    private final String codigo;
    private final boolean ativo;

    @QueryProjection
    public OrgaoDTO(Integer id, int tipo, String nome, String sigla, String codigo, boolean ativo) {
        this.id = id;
        this.tipo = TipoOrgao.values()[tipo].getNome();
        this.nome = nome;
        this.sigla = sigla;
        this.codigo = codigo;
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

    public boolean isAtivo() {
        return ativo;
    }
}
