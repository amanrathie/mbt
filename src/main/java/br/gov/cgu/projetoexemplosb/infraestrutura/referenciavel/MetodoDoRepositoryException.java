package br.gov.cgu.projetoexemplosb.infraestrutura.referenciavel;

public class MetodoDoRepositoryException extends RuntimeException {

    public MetodoDoRepositoryException(String clazz, String metodo, Exception e) {
        super("Não foi possível encontrar o METODO " + metodo + " para a entidade referenciavél. " +
                "Entidade: " + clazz + " -- Repository: " + clazz + "Repository", e);
    }
}
