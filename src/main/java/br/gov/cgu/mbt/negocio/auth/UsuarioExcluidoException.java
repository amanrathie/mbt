package br.gov.cgu.mbt.negocio.auth;

import org.springframework.security.core.AuthenticationException;

public class UsuarioExcluidoException extends AuthenticationException {

    public UsuarioExcluidoException() {
        super("Usuário excluído. Solicite a regularização a um administrador.");
    }
    
    public UsuarioExcluidoException(Usuario usuario) {
        super("O usuário de CPF " + usuario.getCpf() + " está excluído.");
    }
}
