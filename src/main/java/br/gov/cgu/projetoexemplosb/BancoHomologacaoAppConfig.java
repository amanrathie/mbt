package br.gov.cgu.projetoexemplosb;

import com.querydsl.sql.SQLTemplates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;

@Configuration
@Profile(value = "banco-hom")
public class BancoHomologacaoAppConfig {

    @Bean
    public DataSource dataSourceHom() {
        return Application.dataSource("TODO-configurar-usuario-bd","TODO-configurar-senha-bd","jdbc:jtds:sqlserver://hercules-h.df.cgu/TODO-configurar-bd");
    }

    @Bean
    public SQLTemplates sqlTemplateHom() {
        return Application.sqlTemplate();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryHom() {
        return Application.entityManagerFactory(dataSourceHom(), "false","br.gov.cgu.persistencia.jpa.sqlserver.PortalSQLServerDialect");
    }
}
