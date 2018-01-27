package br.gov.cgu.mbt.infraestrutura.referenciavel;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * Anote uma {@link br.gov.cgu.persistencia.jpa.Entidade} com isso e a {@link EntidadesReferenciaveis} vai conseguir
 * chamar os métodos adequados do repositorio para buscar por termo e para recuperar por ID's.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Referenciavel {

    String label();

}
