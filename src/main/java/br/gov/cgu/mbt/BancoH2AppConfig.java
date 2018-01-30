package br.gov.cgu.mbt;

//@Configuration
//@Profile(value = "h2")
public class BancoH2AppConfig {

   /* public static EmbeddedDatabase datasource;

    @Bean
    public DataSource devDataSource() {
        datasource = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .setScriptEncoding("UTF-8")
                .addScripts("classpath:sql/h2options.sql", "classpath:sql/esquema.sql", "classpath:sql/dados.sql")
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
    }*/
}
