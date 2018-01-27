package br.gov.cgu.mbt.infraestrutura.referenciavel;

public class RepositoryNaoEncontradoException extends RuntimeException {

    public RepositoryNaoEncontradoException(String clazz, Exception e) {
        super("Não foi possível encontrar o repositório para a entidade referenciavél. " +
                "Entidade: " + clazz + " -- Repository: " + clazz + "Repository", e);
    }
}
