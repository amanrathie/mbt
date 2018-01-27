package br.gov.cgu.mbt.aplicacao.auth;

import org.springframework.security.core.AuthenticationException;

import br.gov.cgu.mbt.Constantes;

public class UsuarioInativadoPorTempoException extends AuthenticationException {

    public UsuarioInativadoPorTempoException() {
        super("Usuário inativo, pois passou mais de " + Constantes.QTDE_MAXIMA_DIAS_INATIVIDADE_USUARIO + " dias sem utilizar o sistema. " +
                "Faça a solicitação de reativação pelo Sistema Acesso (usuário interno) ou Sistema Cadu (usuário externo).");
    }

}
