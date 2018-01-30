package br.gov.cgu.mbt;

import java.time.Duration;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import net.javacrumbs.shedlock.core.LockProvider;
import net.javacrumbs.shedlock.provider.jdbctemplate.JdbcTemplateLockProvider;
import net.javacrumbs.shedlock.spring.ScheduledLockConfiguration;
import net.javacrumbs.shedlock.spring.ScheduledLockConfigurationBuilder;

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
}
