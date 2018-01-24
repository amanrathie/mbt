package br.gov.cgu.projetoexemplosb;

import br.gov.cgu.projetoexemplosb.aplicacao.auth.Autenticador;
import br.gov.cgu.projetoexemplosb.infraestrutura.adfs.CustomSAMLBootstrap;
import br.gov.cgu.projetoexemplosb.infraestrutura.adfs.AdfsProcessingFilter;
import br.gov.cgu.projetoexemplosb.web.auth.APIAuthenticationFilter;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.opensaml.saml2.metadata.provider.MetadataProviderException;
import org.opensaml.saml2.metadata.provider.ResourceBackedMetadataProvider;
import org.opensaml.util.resource.ClasspathResource;
import org.opensaml.util.resource.ResourceException;
import org.opensaml.xml.parse.ParserPool;
import org.opensaml.xml.parse.StaticBasicParserPool;
import org.opensaml.xml.parse.XMLParserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.saml.*;
import org.springframework.security.saml.context.SAMLContextProviderLB;
import org.springframework.security.saml.key.JKSKeyManager;
import org.springframework.security.saml.log.SAMLDefaultLogger;
import org.springframework.security.saml.metadata.CachingMetadataManager;
import org.springframework.security.saml.metadata.ExtendedMetadata;
import org.springframework.security.saml.metadata.ExtendedMetadataDelegate;
import org.springframework.security.saml.metadata.MetadataDisplayFilter;
import org.springframework.security.saml.parser.ParserPoolHolder;
import org.springframework.security.saml.processor.*;
import org.springframework.security.saml.util.VelocityFactory;
import org.springframework.security.saml.websso.*;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.*;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled=true)
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired private Autenticador autenticador;
    @Autowired private APIAuthenticationFilter apiAuthenticationFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        SAMLEntryPoint samlEntryPoint = getSamlEntryPoint();
        FilterChainProxy samlFilter = getSamlFilter(samlEntryPoint,
                getSamlLogoutFilter(),
                getSamlWebSSOProcessingFilter(autenticador),
                getSamlWebSSOHoKProcessingFilter(),
                getSamlLogoutProcessingFilter(),
                apiAuthenticationFilter);

        http
                .httpBasic()
                    .authenticationEntryPoint(samlEntryPoint)
                .and().authorizeRequests()
                    .regexMatchers("/static.*", "/erro.*").permitAll()
                    .regexMatchers("/auth.*", "/api/auth.*").authenticated()
                .and().csrf().disable()
                .addFilterAfter(samlFilter, BasicAuthenticationFilter.class)
                .headers()
                    .frameOptions().sameOrigin()
                    .xssProtection().disable();
    }

    @Override
    public AuthenticationManager authenticationManager() {
        return new ProviderManager(singletonList(getAuthenticationProvider()));
    }

    @Bean
    public SAMLAuthenticationProvider getAuthenticationProvider() {
        return new SAMLAuthenticationProvider();
    }

    @Bean(value = "metadata")
    public CachingMetadataManager getMetadata(@Autowired ParserPool parserPool) throws ResourceException, MetadataProviderException {
        ExtendedMetadataDelegate idpExtendedMetadataDelegate = getExtendedMetadataDelegate("/idp-metadata.xml", parserPool);
        ExtendedMetadataDelegate spExtendedMetadataDelegate = getExtendedMetadataDelegate("/sp-metadata.xml", parserPool);
        return new CachingMetadataManager(asList(idpExtendedMetadataDelegate, spExtendedMetadataDelegate));
    }

    private ExtendedMetadataDelegate getExtendedMetadataDelegate(String path, ParserPool parserPool) throws ResourceException, MetadataProviderException {
        ClasspathResource idpMetadata = new ClasspathResource(path);
        ResourceBackedMetadataProvider delegate = new ResourceBackedMetadataProvider(new Timer(), idpMetadata);
        delegate.setParserPool(parserPool);
        ExtendedMetadata defaultMetadata = new ExtendedMetadata();
        defaultMetadata.setLocal(true);
        return new ExtendedMetadataDelegate(delegate, defaultMetadata);
    }

    @Bean(value = "parserPool")
    public ParserPool getParserPool() throws XMLParserException {
        StaticBasicParserPool parserPool = new StaticBasicParserPool();
        parserPool.initialize();
        return parserPool;
    }

    @Bean(value = "parserPoolHolder")
    public ParserPoolHolder getParserPoolHolder() { return new ParserPoolHolder(); }

    @Bean
    public JKSKeyManager getKeyManager(@Value("${adfs.jks.alias}") String adfsJksAlias, @Value("${adfs.jks.senha}") String adfsJkdSenha    ) {
        Map<String, String> map = new HashMap<>();
        map.put(adfsJksAlias, adfsJkdSenha);
        return new JKSKeyManager(new ClassPathResource(adfsJksAlias +".jks"), adfsJkdSenha, map, adfsJksAlias);
    }

    @Bean(value = "samlBootstrap")
    public static CustomSAMLBootstrap getSamlBootstrap() { return new CustomSAMLBootstrap(); }

    @Bean(value = "logoutprofile")
    public SingleLogoutProfileImpl getLogoutProfile() { return new SingleLogoutProfileImpl(); }

    @Bean(value = "samlLogger")
    public SAMLDefaultLogger getSamlLogger() { return new SAMLDefaultLogger(); }

    @Bean(value = "webSSOprofileConsumer")
    public WebSSOProfileConsumerImpl getWebSSOprofileConsumer() {
        WebSSOProfileConsumerImpl webSSOProfileConsumer = new WebSSOProfileConsumerImpl();
        webSSOProfileConsumer.setMaxAuthenticationAge(72000000);
        return webSSOProfileConsumer;
    }

    @Bean(value = "webSSOprofile")
    public WebSSOProfile getWebSSOprofile() {
        return new WebSSOProfileImpl();
    }

    @Bean(value = {"hokWebSSOProfile", "hokWebSSOprofileConsumer"})
    public WebSSOProfileConsumerHoKImpl getHokWebSSOProfile() { return new WebSSOProfileConsumerHoKImpl(); }

    @Bean(value = "ecpProfile")
    public WebSSOProfileECPImpl getEcpprofile() { return new WebSSOProfileECPImpl(); }

    @Bean
    public SAMLContextProviderLB getContextProvider(@Value("${contextProvider.serverName}") String serverName,
                                                    @Value("${contextProvider.serverPort}") int serverPort,
                                                    @Value("${contextProvider.includeServerPortInRequestURL}") boolean includeServerPortInRequestURL,
                                                    @Value("${contextProvider.contextPath}") String contextPath) {
        SAMLContextProviderLB provider = new SAMLContextProviderLB();
        provider.setScheme("https");
        provider.setServerName(serverName);
        provider.setServerPort(serverPort);
        provider.setIncludeServerPortInRequestURL(includeServerPortInRequestURL);
        provider.setContextPath(contextPath);
        return provider;
    }

    @Bean
    public SAMLEntryPoint getSamlEntryPoint() {
        SAMLEntryPoint samlEntryPoint = new SAMLEntryPoint();
        WebSSOProfileOptions defaultOptions = new WebSSOProfileOptions();
        defaultOptions.setIncludeScoping(false);
        samlEntryPoint.setDefaultProfileOptions(defaultOptions);
        return samlEntryPoint;
    }

    @Bean
    public SecurityContextLogoutHandler getLogoutHandler() {
        SecurityContextLogoutHandler handler = new SecurityContextLogoutHandler();
        handler.setInvalidateHttpSession(true);
        return handler;
    }

    @Bean
    public SavedRequestAwareAuthenticationSuccessHandler getSuccessRedirectHandler() {
        SavedRequestAwareAuthenticationSuccessHandler handler = new SavedRequestAwareAuthenticationSuccessHandler();
        handler.setDefaultTargetUrl("/");
        return handler;
    }

    @Bean
    public SimpleUrlAuthenticationFailureHandler getFailureRedirectHandler() {
        SimpleUrlAuthenticationFailureHandler handler = new SimpleUrlAuthenticationFailureHandler();
        handler.setUseForward(true);
        handler.setDefaultFailureUrl("/erro/403");
        return handler;
    }

    @Bean
    public SimpleUrlLogoutSuccessHandler getSuccessLogoutHandler() {
        SimpleUrlLogoutSuccessHandler handler = new SimpleUrlLogoutSuccessHandler();
        handler.setDefaultTargetUrl("/");
        return handler;
    }

    @Bean
    public SAMLLogoutFilter getSamlLogoutFilter() {
        SecurityContextLogoutHandler[] logoutHandler = new SecurityContextLogoutHandler[]{getLogoutHandler()};
        return new SAMLLogoutFilter(getSuccessLogoutHandler(), logoutHandler, logoutHandler);
    }

    @Bean
    public AdfsProcessingFilter getSamlWebSSOProcessingFilter(@Autowired Autenticador autenticador){
        AdfsProcessingFilter filter = new AdfsProcessingFilter();
        filter.setAuthenticationManager(authenticationManager());
        filter.setAuthenticationSuccessHandler(getSuccessRedirectHandler());
        filter.setAuthenticationFailureHandler(getFailureRedirectHandler());
        filter.setAutenticador(autenticador);
        return filter;
    }

    @Bean
    public SAMLWebSSOHoKProcessingFilter getSamlWebSSOHoKProcessingFilter() {
        SAMLWebSSOHoKProcessingFilter filter = new SAMLWebSSOHoKProcessingFilter();
        filter.setAuthenticationManager(authenticationManager());
        filter.setAuthenticationSuccessHandler(getSuccessRedirectHandler());
        filter.setAuthenticationFailureHandler(getFailureRedirectHandler());
        return filter;
    }

    @Bean
    public SAMLLogoutProcessingFilter getSamlLogoutProcessingFilter() {
        return new SAMLLogoutProcessingFilter(getSuccessLogoutHandler(), getLogoutHandler());
    }

    @Bean
    public SAMLProcessorImpl getProcessor(@Autowired ParserPool parserPool) throws XMLParserException {
        ArtifactResolutionProfileImpl artifactResolutionProfile = new ArtifactResolutionProfileImpl(new HttpClient(new MultiThreadedHttpConnectionManager()));
        HTTPPAOS11Binding httpBinding = new HTTPPAOS11Binding(parserPool);
        artifactResolutionProfile.setProcessor(new SAMLProcessorImpl(httpBinding));
        return new SAMLProcessorImpl(
                asList(new HTTPRedirectDeflateBinding(parserPool),
                        new HTTPPostBinding(parserPool, VelocityFactory.getEngine()),
                        new HTTPArtifactBinding(parserPool, VelocityFactory.getEngine(), artifactResolutionProfile),
                        httpBinding,
                        httpBinding
                )
        );
    }

    @Bean
    public FilterChainProxy getSamlFilter(
            @Autowired SAMLEntryPoint samlEntryPoint,
            @Autowired SAMLLogoutFilter samlLogoutFilter,
            @Autowired AdfsProcessingFilter samlWebSSOProcessingFilter,
            @Autowired SAMLWebSSOHoKProcessingFilter samlWebSSOHoKProcessingFilter,
            @Autowired SAMLLogoutProcessingFilter samlLogoutProcessingFilter,
            @Autowired APIAuthenticationFilter apiAuthenticationFilter)
    {
        List<SecurityFilterChain> listOfFilterChains = new ArrayList<>();
        listOfFilterChains.add(new DefaultSecurityFilterChain(new AntPathRequestMatcher("/saml/login/**"), samlEntryPoint));
        listOfFilterChains.add(new DefaultSecurityFilterChain(new AntPathRequestMatcher("/saml/logout/**"      ), samlLogoutFilter));
        listOfFilterChains.add(new DefaultSecurityFilterChain(new AntPathRequestMatcher("/saml/metadata/**"    ), new MetadataDisplayFilter()));
        listOfFilterChains.add(new DefaultSecurityFilterChain(new AntPathRequestMatcher("/saml/SSO/**"         ), samlWebSSOProcessingFilter));
        listOfFilterChains.add(new DefaultSecurityFilterChain(new AntPathRequestMatcher("/saml/SSOHoK/**"      ), samlWebSSOHoKProcessingFilter));
        listOfFilterChains.add(new DefaultSecurityFilterChain(new AntPathRequestMatcher("/saml/SingleLogout/**"), samlLogoutProcessingFilter));
        listOfFilterChains.add(new DefaultSecurityFilterChain(new AntPathRequestMatcher("/api/**"              ), apiAuthenticationFilter));
        return new FilterChainProxy(listOfFilterChains);
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
        return new PropertySourcesPlaceholderConfigurer();
    }

}
