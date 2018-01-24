package br.gov.cgu.projetoexemplosb.negocio.unidade;

public enum TipoUnidade {
    UAIG("Unidade de Auditoria Interna Governamental"),
    UAUD("Unidade Auditada"),
    UPAG("Unidade Pagadora");

    private final String descricao;

    TipoUnidade(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }
}
