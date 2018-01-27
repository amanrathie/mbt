package br.gov.cgu.mbt.aplicacao.email;

import java.util.ArrayList;
import java.util.List;

import br.gov.cgu.mbt.negocio.auth.Usuario;

public class UsuarioBuilder {
    public static List<Usuario> criarLista() {
        List<Usuario> usuarios = new ArrayList<>();
        usuarios.add(criar(1, "Amedeo Avogadro", "amedeo@email.gov.br"));
        usuarios.add(criar(2, "Dmitri Mendeleev", "dmitri@email.gov.br"));
        usuarios.add(criar(3, "Ernest Rutherford", "ernest@email.gov.br"));

        return usuarios;
    }

    public static Usuario criar() {
        return criar(1, "Amedeo Avogadro", "amedeo@email.gov.br");
    }

    public static Usuario criar(Integer id, String nome, String email) {
        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setNome(nome);
        usuario.setEmail(email);

        return usuario;
    }
}
