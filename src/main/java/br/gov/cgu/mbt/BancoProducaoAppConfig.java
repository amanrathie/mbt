package br.gov.cgu.mbt;

import com.querydsl.sql.SQLTemplates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;

@Configuration
@Profile(value = "banco-prod")
public class BancoProducaoAppConfig {

    @Bean
    public DataSource dataSourceProd() {
        return Application.dataSource("TODO-configurar-usuario-bd","TODO-configurar-senha-bd","jdbc:jtds:sqlserver://TODO-configurar-banco.cgu.local/TODO-configurar-bd");
    }

    @Bean
    public SQLTemplates sqlTemplateProd() {
        return Application.sqlTemplate();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryProd() {
        return Application.entityManagerFactory(dataSourceProd(), "false","br.gov.cgu.persistencia.jpa.sqlserver.PortalSQLServerDialect");
    }
}
