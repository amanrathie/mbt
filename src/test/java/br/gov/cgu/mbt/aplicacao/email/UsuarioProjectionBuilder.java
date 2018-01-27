package br.gov.cgu.mbt.aplicacao.email;

import br.gov.cgu.mbt.negocio.auth.UsuarioProjection;
import br.gov.cgu.utils.CpfCnpjUtils;

import java.util.ArrayList;
import java.util.List;

public class UsuarioProjectionBuilder {
    private UsuarioProjectionBuilder() {
    }

    public static List<UsuarioProjection> criarLista() {
        List<UsuarioProjection> usuarios = new ArrayList<>();
        usuarios.add(criar(1, "Frederic Bastiat", "12345678910", "49698040", "bastiat", "bastiat@escolaaustriaca.com", true));
        usuarios.add(criar(2, "Friedrich Hayek", "49678404040", "94873045", "hayek", "hayek@escolaaustriaca.com", true));
        usuarios.add(criar(3, "Murray Rothbard", "49493004939", "19848043", "rothbard", "rothbard@escolaaustriaca.com", true));

        return usuarios;
    }

    public static UsuarioProjection criar() {
        return criar(
                6,
                "Anselmo Júlio da Rocha",
                "00000000006",
                "12345678",
                "CGU\\anselmojr",
                "anselmo@cgu.gov.br",
                true
        );
    }

    public static UsuarioProjection criar(Integer id, String nome, String cpf, String siape, String login, String email, boolean ativo) {
        UsuarioProjection usuarioProjection = new UsuarioProjection();
        usuarioProjection.setId(id);
        usuarioProjection.setNome(nome);
        usuarioProjection.setCpf(CpfCnpjUtils.formatarCpf(cpf));
        usuarioProjection.setSiape(siape);
        usuarioProjection.setLogin(login);
        usuarioProjection.setEmail(email);
        usuarioProjection.setAtivo(ativo);

        return usuarioProjection;
    }
}
