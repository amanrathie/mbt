package br.gov.cgu.mbt;

import com.querydsl.sql.SQLTemplates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;

@Configuration
@Profile(value = "banco-dev-local")
public class BancoDevLocalAppConfig {
    @Bean
    public DataSource dataSourceDev() {
        return Application.dataSource("sa","MINHA_SENHA","jdbc:jtds:sqlserver://localhost/mbt;instance=SQLEXPRESS");
    }

    @Bean
    public SQLTemplates sqlTemplateDev() {
        return Application.sqlTemplate();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryDev() {
        return Application.entityManagerFactory(dataSourceDev(), "true","br.gov.cgu.persistencia.jpa.sqlserver.PortalSQLServerDialect");
    }
}
