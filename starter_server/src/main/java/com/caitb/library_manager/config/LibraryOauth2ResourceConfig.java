package com.caitb.library_manager.config;

import com.caitb.library_manager.components.security.LibraryAccessDeniedHandler;
import com.caitb.library_manager.components.security.LibraryAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * @author
 */
@EnableResourceServer
@Configuration
public class LibraryOauth2ResourceConfig extends ResourceServerConfigurerAdapter {

    @Value("${security.oauth2.resource.id}")
    public String resourceId;

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private LibraryAuthenticationEntryPoint authenticationEntryPoint;

//    @Bean
//    public LibraryTokenAuthenticationConfig getTokenFilter() {
//        return new LibraryTokenAuthenticationConfig(tokenStore);
//    }

    @Autowired
    private LibraryAccessDeniedHandler libraryAccessDeniedHandler;

    @Override
    public void configure(HttpSecurity http) throws Exception {

        http.cors();

        // 设置一些基础的属性
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
                .anyRequest()
                .authenticated();

        http.exceptionHandling()
        .accessDeniedHandler(libraryAccessDeniedHandler);
    }

    @Bean
    public DefaultTokenServices libraryTokenService() {
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setTokenStore(tokenStore);
        tokenServices.setSupportRefreshToken(true);//允许支持刷新token
        tokenServices.setAccessTokenValiditySeconds(60 * 60 * 24); // accessToken有效时间：1天
        tokenServices.setRefreshTokenValiditySeconds(60 * 60 * 24);//refreshToken有效时间：1天
        tokenServices.setReuseRefreshToken(true);
        return tokenServices;
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.tokenStore(tokenStore)
                .resourceId(resourceId)
                .tokenServices(libraryTokenService());

        resources.authenticationEntryPoint(authenticationEntryPoint);

    }

}
