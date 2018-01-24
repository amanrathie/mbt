package br.gov.cgu.projetoexemplosb;

import com.querydsl.sql.SQLTemplates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;

@Configuration
@Profile(value = "banco-dev")
public class BancoDevAppConfig {
    @Bean
    public DataSource dataSourceDev() {
        return Application.dataSource("TODO-configurar-usuario-bd","TODO-configurar-senha-bd","jdbc:jtds:sqlserver://hercules-d.df.cgu/TODO-configurar-bd");
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
