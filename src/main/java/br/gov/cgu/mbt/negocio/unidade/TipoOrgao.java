package br.gov.cgu.mbt.negocio.unidade;

public enum TipoOrgao {
    ADMINISTRACAO_DIRETA("Administração Direta"),
    ADMINISTRACAO_DIRETA_ESTADUAL("Administração Direta Estadual"),
    ADMINISTRACAO_DIRETA_MUNICIPAL("Administração Direta Municipal"),
    ADMINISTRACAO_INDIRETA_ESTADUAL("Administração Indireta Estadual"),
    ADMINISTRACAO_INDIRETA_MUNICIPAL("Administração Indireta Municipal"),
    AUTARQUIA("Autarquia"),
    FUNDACAO("Fundação"),
    EMPRESA_PUBLICA_COMERCIAL_FINANCEIRA("Empresa Pública e Comercial Financeira"),
    ECONOMIA_MISTA("Economia Mista"),
    FUNDS("Fundos"),
    EMPRESA_PUBLICA_INDUSTRIAL_E_AGRICOLA("Empresa Pública Industrial e Agrícola"),
    EMPRESA_PRIVADA("Empresa Privada"),
    ORGANIZACAO_SOCIAL("Organização Social"),
    SERVICO_SOCIAL_AUTONOMO("Serviço Social Autônomo");

    private final String nome;

    TipoOrgao(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return nome;
    }
}
