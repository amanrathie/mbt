package integracao;

import br.gov.cgu.projetoexemplosb.BancoH2AppConfig;
import br.gov.cgu.projetoexemplosb.Application;
import io.github.seleniumquery.SeleniumQuery;
import org.apache.commons.lang3.SystemUtils;
import org.hibernate.Cache;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

public abstract class BootIntegracaoTest {
    private final Logger logger = LoggerFactory.getLogger(BootIntegracaoTest.class);

    private static ConfigurableApplicationContext applicationContext;

    static {
        prepararDriverSelenium();
        startSpringBoot();
    }

    private static void startSpringBoot() {
        System.setProperty("spring.profiles.active", "http,h2,login");
        applicationContext = SpringApplication.run(Application.class);
        applicationContext.registerShutdownHook();
    }

    private static void prepararDriverSelenium() {
        System.setProperty("webdriver.chrome.driver",
                SystemUtils.IS_OS_WINDOWS ? "target/test-classes/chromedriver_win32.exe" : "target/test-classes/chromedriver_linux64");
//        System.setProperty("webdriver.chrome.logfile", "c:\\tmp\\chromedriver.log");
//        System.setProperty("webdriver.chrome.verboseLogging", "true");

        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--headless");
        ChromeDriver driver = new ChromeDriver(options);
        SeleniumQuery.$.driver().use(driver);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> $.driver().quit()));
    }

    /*
    Alguns testes estão rodando mais rapido que o hibernate na hora de salvar a entidade, aí o teste falha.
    */
    public static void refreshSeDer404(String urlDestino) {
        WebDriverWait wait = new WebDriverWait($.driver().get(), 10);
        wait.until(ExpectedConditions.urlContains(urlDestino));
        if ($(".e404").size() > 0) {
            $.url($.url() + "&refresh=aaa");
        }
    }

    @Before
    public void resetarDataSourceEntreOsTestes() throws Exception {
        logger.info("------------------- RESETANDO DADOS DO H2 ------------------------------");
        DefaultResourceLoader resourceLoader = new DefaultResourceLoader();
        DatabasePopulator populator = new ResourceDatabasePopulator(
                resourceLoader.getResource("classpath:sql/reset.sql"),
                resourceLoader.getResource("classpath:sql/esquema.sql"),
                resourceLoader.getResource("classpath:sql/dados.sql")
        );

        DatabasePopulatorUtils.execute(populator, BancoH2AppConfig.datasource);

        Cache cache = applicationContext.getBean(SessionFactory.class).getCache();
        cache.evictAllRegions();
    }

    /**
     * Testa se a URL contém a string
     */
    protected void assertUrlContains(String s) {
        assertThat($.url(), containsString(s));
    }

}