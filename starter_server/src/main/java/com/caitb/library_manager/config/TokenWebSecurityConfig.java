package com.caitb.library_manager.config;

import com.caitb.library_manager.components.security.LibraryAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * 核心web配置类
 * @author
 */
@EnableWebSecurity
@Configurable
public class TokenWebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

//    @Bean
//    public LibraryTokenAuthenticationConfig libraryTokenAuthenticationConfig() {
//        return new LibraryTokenAuthenticationConfig();
//    }

    /**
     * 用户数据查询
     */
    @Autowired
    @Qualifier("customUserDetailService")
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
    }

    @Autowired
    private LibraryAuthenticationEntryPoint authenticationEntryPoint;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.cors();
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/oauth/**",
                        "/login", "/swagger-ui.html", "/oauth/public/**",
                        "/webjars/**",
                        "webjars/springfox-swagger-ui/**",
                        "webjars/springfox-swagger-ui",
                        "/configuration/**",
                        "/swagger-ui.html",
                        "/static/**",
                        "/v2/api-docs**",
                        "/swagger-resources/**",
                        "/druid/**",
                        "/oauth/**",
                        "/actuator/**",
                        "/csrf")
                .permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().and()
                .httpBasic();

        http.exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint);
    }

    //AuthenticationManager对象在OAuth2认证服务中要使用，提取放入IOC容器中
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
		AuthenticationManager authenticationManager = super.authenticationManagerBean();
		return authenticationManager;
	}

    private CorsConfiguration corsConfig() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();

        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setMaxAge(3600L);
        return corsConfiguration;
    }
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig());
        return new CorsFilter(source);
    }
}
