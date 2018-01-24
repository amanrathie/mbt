package br.gov.cgu.projetoexemplosb.aplicacao.upload;

public class UploadException extends RuntimeException {

    public UploadException(String mensagem) {
        super(mensagem);
    }

    public UploadException(Exception e) {
        super("Aconteceu um erro desconhecido ao realizar o upload do arquivo.", e);
    }
}
