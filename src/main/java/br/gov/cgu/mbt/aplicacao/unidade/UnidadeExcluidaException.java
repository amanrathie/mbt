package br.gov.cgu.mbt.aplicacao.unidade;

import org.springframework.security.core.AuthenticationException;

import br.gov.cgu.mbt.negocio.unidade.Unidade;

public class UnidadeExcluidaException extends AuthenticationException {
    public UnidadeExcluidaException(Unidade unidade) {
        super("A unidade de ID " + unidade.getId() + " está excluída.");
    }
}
