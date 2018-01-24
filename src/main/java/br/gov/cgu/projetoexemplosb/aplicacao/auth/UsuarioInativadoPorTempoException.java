package br.gov.cgu.projetoexemplosb.aplicacao.auth;

import br.gov.cgu.projetoexemplosb.Constantes;
import org.springframework.security.core.AuthenticationException;

public class UsuarioInativadoPorTempoException extends AuthenticationException {

    public UsuarioInativadoPorTempoException() {
        super("Usuário inativo, pois passou mais de " + Constantes.QTDE_MAXIMA_DIAS_INATIVIDADE_USUARIO + " dias sem utilizar o sistema. " +
                "Faça a solicitação de reativação pelo Sistema Acesso (usuário interno) ou Sistema Cadu (usuário externo).");
    }

}
