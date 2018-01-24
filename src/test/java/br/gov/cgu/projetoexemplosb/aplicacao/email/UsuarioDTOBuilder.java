package br.gov.cgu.projetoexemplosb.aplicacao.email;

import br.gov.cgu.projetoexemplosb.aplicacao.auth.UsuarioDTO;

import java.util.ArrayList;
import java.util.List;

public class UsuarioDTOBuilder {
    private UsuarioDTOBuilder() {
    }

    public static List<UsuarioDTO> criarLista() {
        List<UsuarioDTO> usuarios = new ArrayList<>();
        usuarios.add(new UsuarioDTO(1, "Frederic Bastiat", "12345678910", "49698040", "bastiat", "bastiat@escolaaustriaca.com", true));
        usuarios.add(new UsuarioDTO(2, "Friedrich Hayek", "49678404040", "94873045", "hayek", "hayek@escolaaustriaca.com", true));
        usuarios.add(new UsuarioDTO(3, "Murray Rothbard", "49493004939", "19848043", "rothbard", "rothbard@escolaaustriaca.com", true));

        return usuarios;
    }

    public static UsuarioDTO criar() {
        return new UsuarioDTO(
            6,
            "Anselmo Júlio da Rocha",
            "00000000006",
            "12345678",
            "CGU\\anselmojr",
            "anselmo@cgu.gov.br",
            true
        );
    }
}