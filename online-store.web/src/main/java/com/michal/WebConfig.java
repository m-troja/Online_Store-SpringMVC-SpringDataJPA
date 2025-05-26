package com.michal;

import java.util.Properties;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.michal.onlinestore.web.security.DefaultAuthenticationFailureHandler;
import com.michal.onlinestore.web.security.DefaultAuthenticationSuccessHandler;
import com.michal.onlinestore.web.security.DefaultSuccessLogoutHandler;
import com.michal.onlinestore.web.security.DefaultUserDetailsService;

@EnableAspectJAutoProxy
@EnableWebSecurity
@EnableWebMvc
@Configuration
@PropertySource("classpath:app.properties")
@ComponentScan(basePackages = { "com.michal.onlinestore"})
@EnableTransactionManagement
@EnableJpaRepositories("com.michal.onlinestore.persistence.repo")
@PropertySource("classpath:database.properties")
public class WebConfig implements WebMvcConfigurer {

    // Configures how views (JSPs) are resolved by Spring MVC
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver bean = new InternalResourceViewResolver();
        bean.setViewClass(JstlView.class);
        bean.setPrefix("/WEB-INF/views/");
        bean.setSuffix(".jsp");
        return bean;
    }

    // Handles exceptions and directs them to an error view
    @Bean
    public HandlerExceptionResolver errorHandler() {
        return new HandlerExceptionResolver() {
            @Override
            public ModelAndView resolveException(HttpServletRequest request,
                                                 HttpServletResponse response,
                                                 Object handler,
                                                 Exception ex) {
                ModelAndView model = new ModelAndView("error-page");
                ex.printStackTrace();
                model.addObject("exception", ex);
                model.addObject("handler", handler);
                return model;
            }
        };
    }

    // Serves static resources like CSS, JS, fonts, etc.
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
            .addResourceHandler("/css/**", "/fonts/**", "/images/**", "/js/**", "/vendor/**")
            .addResourceLocations("/css/", "/fonts/", "/images/", "/js/", "/vendor/");
    }

    // Loads internationalized message properties
    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource ();
        messageSource.setBasenames("classpath:OnlineShopResourceBundle");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    // Resolves user locale settings using cookies
    @Bean
    public LocaleResolver localeResolver() {
        return new CookieLocaleResolver();
    }

    // Adds support for changing locale via request parameter
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("lang");
        registry.addInterceptor(localeChangeInterceptor);
    }

    // Defines the data source used by the application
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setUrl(environment.getProperty(PROPERTY_URL));
        ds.setUsername(environment.getProperty(PROPERTY_USERNAME));
        ds.setPassword(environment.getProperty(PROPERTY_PASSWORD));
        ds.setDriverClassName(environment.getProperty(PROPERTY_DRIVER));
        return ds;
    }

    // Configures the JPA EntityManager factory
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean lfb = new LocalContainerEntityManagerFactoryBean();
        lfb.setDataSource(dataSource());
        lfb.setPackagesToScan("com.michal.onlinestore");
        lfb.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        lfb.setJpaProperties(hibernateProps());
        return lfb;
    }

/*
 * JPA & Hibernate
 */
    
    private static final String PROPERTY_DRIVER = "driver";
    private static final String PROPERTY_URL = "url";
    private static final String PROPERTY_USERNAME = "user";
    private static final String PROPERTY_PASSWORD = "password";
    private static final String PROPERTY_SHOW_SQL = "hibernate.show_sql";
    private static final String PROPERTY_DIALECT = "hibernate.dialect";
    
    // Sets JPA-specific Hibernate properties
    private Properties hibernateProps() {
        Properties properties = new Properties();
        properties.setProperty(PROPERTY_DIALECT, environment.getProperty(PROPERTY_DIALECT));
        properties.setProperty(PROPERTY_SHOW_SQL, environment.getProperty(PROPERTY_SHOW_SQL));
        return properties;
    }

    // Configures the transaction manager for JPA
    @Bean
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }

    // Sets up the password encoder for user credentials
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /*
     * Security
     */
    
    
    // Configures HTTP security, login, logout, and access rules
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/management*").hasRole("MANAGER")
                .requestMatchers("/my-profile").authenticated()
            )
            .formLogin(form -> form
                .usernameParameter("email")
                .loginPage("/signin")
                .loginProcessingUrl("/perform_login")
                .defaultSuccessUrl("/homepage", false)
                .successHandler(successHandler())
                .failureUrl("/signin?error=true")
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/perform_logout")
                .deleteCookies("JSESSIONID")
                .permitAll()
                .logoutSuccessHandler(logoutSuccessHandler())
            )
            .rememberMe(rememberMe -> rememberMe
                .key("superSecretKey")
                .rememberMeParameter("remember")
                .rememberMeCookieName("rememberlogin")
            );

        return http.build();
    }



    // Handles login failure events
    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new DefaultAuthenticationFailureHandler();
    }

    // Handles successful logout events
    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() {
        return new DefaultSuccessLogoutHandler();
    }

    // Handles successful login events
    @Bean
    public AuthenticationSuccessHandler successHandler() {
        return new DefaultAuthenticationSuccessHandler();
    }

    // Provides custom user authentication logic
    @Bean
    public UserDetailsService userDetailsService() {
        return new DefaultUserDetailsService();
    }

    // Environment used to load database properties
    @Autowired
    private Environment environment;

}
