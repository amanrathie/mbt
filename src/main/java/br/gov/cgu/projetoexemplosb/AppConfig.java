package br.gov.cgu.projetoexemplosb;

import br.gov.cgu.projetoexemplosb.web.auth.APIAuthenticationFilter;
import net.javacrumbs.shedlock.core.LockProvider;
import net.javacrumbs.shedlock.provider.jdbctemplate.JdbcTemplateLockProvider;
import net.javacrumbs.shedlock.spring.ScheduledLockConfiguration;
import net.javacrumbs.shedlock.spring.ScheduledLockConfigurationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.time.Duration;

@Configuration
public class AppConfig {

    //-------
    //Shedlock
    @Bean
    public ScheduledLockConfiguration taskScheduler(LockProvider lockProvider) {
        return ScheduledLockConfigurationBuilder
                .withLockProvider(lockProvider)
                .withPoolSize(10)
                .withDefaultLockAtMostFor(Duration.ofMinutes(30))
                .build();
    }

    @Bean
    public LockProvider lockProvider(DataSource devDataSource) {
        return new JdbcTemplateLockProvider(devDataSource, Constantes.SCHEMA_APLICACAO + ".Shedlock");
    }

    @Bean
    public FilterRegistrationBean someFilterRegistration(APIAuthenticationFilter filter) {
        FilterRegistrationBean registration = new FilterRegistrationBean(filter);
        registration.setEnabled(false);
        return registration;
    }

}
