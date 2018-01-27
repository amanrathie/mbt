package br.gov.cgu.mbt.negocio.unidade;

public class OrgaoForm {

    private Integer id;
    private TipoOrgao tipo;
    private String nome;
    private String sigla;
    private String codigo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TipoOrgao getTipo() {
        return tipo;
    }

    public void setTipo(TipoOrgao tipo) {
        this.tipo = tipo;
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
}
