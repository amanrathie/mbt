package br.gov.cgu.mbt.aplicacao.email;

public class EmailNaoEnviadoException extends RuntimeException {

    public EmailNaoEnviadoException(Exception e) {
        super("Não foi possível enviar um e-mail.", e);
    }

}
