package com.cicloi.config;

import com.cicloi.services.MyUserDatailService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    private TokenStore tokenStore = new InMemoryTokenStore();

    private final MyUserDatailService myUserDatailService;

    private final AuthenticationManager authenticationManager;

    public AuthorizationServerConfiguration(@Qualifier("authenticationManagerBean") AuthenticationManager authenticationManager, MyUserDatailService myUserDatailService) {
        this.authenticationManager = authenticationManager;
        this.myUserDatailService = myUserDatailService;
    }

    public void configure (AuthorizationServerEndpointsConfigurer configurer){
        configurer.tokenStore(tokenStore)
                .userDetailsService(myUserDatailService)
                .authenticationManager(authenticationManager);
    }

    //definimos clientes que acessa a aplicação
    public void configure (ClientDetailsServiceConfigurer clients) throws Exception{
        clients
                .inMemory()
                .withClient("mobile")
                .authorizedGrantTypes("password", "authorization_code", "refresh_token")
                .scopes("bar", "read", "write")
                //tempo em segundos de token e token refresh
                .refreshTokenValiditySeconds(600)
                .accessTokenValiditySeconds(120)
                .secret("123");

    }

    //definir toker service default
    @Bean
    @Primary
    public DefaultTokenServices tokenServices(){
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setSupportRefreshToken(true);
        tokenServices.setTokenStore(tokenStore);
        return tokenServices;
    }
}
