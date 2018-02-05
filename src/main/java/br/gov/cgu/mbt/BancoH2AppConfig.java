package br.gov.cgu.mbt;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import com.querydsl.sql.H2Templates;
import com.querydsl.sql.SQLTemplates;

@Configuration
@Profile(value = "h2")
public class BancoH2AppConfig {

    public static EmbeddedDatabase datasource;

    @Bean
    public DataSource devDataSource() { // TODO: botar parametros para ficar compatível com o SQL Server
        datasource = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .setScriptEncoding("UTF-8")
                //.addScripts("classpath:sql/h2options.sql", "classpath:sql/esquema.sql", "classpath:sql/dados.sql")
                .build();
        return datasource;
    }

    @Bean
    public SQLTemplates sqlTemplate() {
        return H2Templates.builder().printSchema().build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        return Application.entityManagerFactory(devDataSource(), "true", "br.gov.cgu.persistencia.jpa.h2.PortalH2Dialect");
    }
}

