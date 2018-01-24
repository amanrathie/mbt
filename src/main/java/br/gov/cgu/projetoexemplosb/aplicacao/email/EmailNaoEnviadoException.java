package br.gov.cgu.projetoexemplosb.aplicacao.email;

public class EmailNaoEnviadoException extends RuntimeException {

    public EmailNaoEnviadoException(Exception e) {
        super("Não foi possível enviar um e-mail.", e);
    }

}
