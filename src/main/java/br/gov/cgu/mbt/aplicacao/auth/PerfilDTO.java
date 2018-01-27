package br.gov.cgu.mbt.aplicacao.auth;

public class PerfilDTO {

    private final Integer id;
    private final String nome;
    private final boolean externo;

    public PerfilDTO(Integer id, String nome, boolean externo) {
        this.id = id;
        this.nome = nome;
        this.externo = externo;
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public boolean isExterno() {
        return externo;
    }
}
