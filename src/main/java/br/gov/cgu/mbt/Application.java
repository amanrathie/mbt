package br.gov.cgu.mbt;

import com.querydsl.sql.SQLServer2012Templates;
import com.querydsl.sql.SQLTemplates;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.persistence.ValidationMode;
import javax.sql.DataSource;
import java.util.Properties;

@SpringBootApplication(scanBasePackages = "br.gov.cgu")
@EnableScheduling
@EnableAsync
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	static DataSource dataSource(String usuario, String password, String server) {
		org.apache.tomcat.jdbc.pool.DataSource dataSource = (org.apache.tomcat.jdbc.pool.DataSource) DataSourceBuilder
				.create()
				.username(usuario)
				.password(password)
				.url(server)
				.driverClassName("net.sourceforge.jtds.jdbc.Driver")
				.build();
		dataSource.setValidationQuery("select 1");
		dataSource.setTestOnBorrow(true);
		return dataSource;
	}

	static SQLTemplates sqlTemplate() {
		return SQLServer2012Templates.builder().printSchema().build();
	}

	static LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, String showSql, String dialeto) {
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setValidationMode(ValidationMode.NONE);
		factory.setPackagesToScan("br.gov.cgu.*");
		factory.setPersistenceProviderClass(HibernatePersistenceProvider.class);
		Properties jpaProperties = new Properties();

		jpaProperties.put("hibernate.archive.autodetection", "class");
		jpaProperties.put("hibernate.show_sql", showSql);
		jpaProperties.put("hibernate.dialect", dialeto);
		jpaProperties.put("hibernate.generate_statistics", "false");
		jpaProperties.put("org.hibernate.envers.default_schema", "dbo");
		jpaProperties.put("org.hibernate.envers.audit_table_suffix", "Log");
		jpaProperties.put("hibernate.hbm2ddl.auto", "none");

		//Cache
		jpaProperties.put("javax.persistence.sharedCache.mode", "ALL");
		jpaProperties.put("hibernate.cache.use_second_level_cache", "true");
		jpaProperties.put("hibernate.cache.use_query_cache", "true");
		jpaProperties.put("hibernate.cache.region.factory_class", "org.hibernate.cache.ehcache.EhCacheRegionFactory");

		factory.setJpaProperties(jpaProperties);
		factory.setDataSource(dataSource);
		return factory;
	}

}
