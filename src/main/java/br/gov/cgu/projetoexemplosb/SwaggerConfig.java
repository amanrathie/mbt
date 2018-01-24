package br.gov.cgu.projetoexemplosb;

import br.gov.cgu.projetoexemplosb.web.auth.APIAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
@Profile(value = "swagger")
public class SwaggerConfig extends WebMvcConfigurerAdapter {

    @Bean
    public Docket docket() {
        return new Docket(springfox.documentation.spi.DocumentationType.SWAGGER_2)
                                .select()
                                .apis(RequestHandlerSelectors.basePackage("br.gov.cgu.projetoexemplosb.web"))
                                .paths(PathSelectors.ant("/api/**"))
                                .build()
                                .securitySchemes(Collections.singletonList(apiKey()))
                                .apiInfo(apiInfo());
    }

    @Bean
    public SecurityScheme apiKey() {
        return new ApiKey(APIAuthenticationFilter.HEADER_CHAVE_API, APIAuthenticationFilter.HEADER_CHAVE_API, "header");
    }

    private ApiInfo apiInfo() {
        Contact contato = new Contact("Diretoria de Tecnologia da Informação - DTI",
                                        "http://www.cgu.gov.br",
                                        "cosis@cgu.gov.br");
        return new ApiInfo(
                "API REST do "+ Constantes.NOME_APLICACAO,
                "API de serviços do "+ Constantes.NOME_APLICACAO,
                "1.0",
                "http://www.planalto.gov.br/ccivil_03/_ato2015-2018/2016/decreto/D8777.htm",
                contato,
                "Decreto nº 8.777, de 11 de maio de 2016",
                "http://www.planalto.gov.br/ccivil_03/_ato2015-2018/2016/decreto/D8777.htm",
                Collections.emptyList()
        );
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/swagger-ui.html").setViewName("forward:/static/api.html");
    }

    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}