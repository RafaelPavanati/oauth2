package com.cicloi.config;

import com.cicloi.services.MyUserDatailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final MyUserDatailService userDetailsService;

    public WebSecurityConfiguration(MyUserDatailService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(userDetailsService);
    }

    @Bean("authenticationManagerBean")
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    //definir urls e metodos que não precisar de autenticação
    @Override
    public void configure(WebSecurity webSecurity) {
        webSecurity.ignoring().antMatchers(HttpMethod.OPTIONS, "*/**").
                antMatchers(HttpMethod.GET, "/public/**");
    }

}
