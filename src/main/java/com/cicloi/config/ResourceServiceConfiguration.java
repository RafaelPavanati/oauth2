package com.cicloi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@Configuration
@EnableResourceServer
public class ResourceServiceConfiguration extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(ResourceServerSecurityConfigurer resource) {
        resource.resourceId("restservice");
    }

    //definir regras de acesso e roles de usuario
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .logout()
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .and().authorizeRequests()
                .antMatchers(HttpMethod.GET, "/usuario/**").hasAnyRole("ADMIN")
                .anyRequest().denyAll();
    }

}
