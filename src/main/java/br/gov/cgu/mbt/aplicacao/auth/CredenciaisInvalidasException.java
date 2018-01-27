package br.gov.cgu.mbt.aplicacao.auth;

import org.springframework.security.core.AuthenticationException;

public class CredenciaisInvalidasException extends AuthenticationException {

    public CredenciaisInvalidasException() {
        super("Credenciais inválidas. Faça o login corretamente.");
    }
}
