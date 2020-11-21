package com.sachin.security.tutorial;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;

import javax.sql.DataSource;

/*@Configuration
public class AuthroizationServerConfig extends AuthorizationServerConfigurerAdapter {

    *//*@Autowired
    DataSource dataSource;
*//*

    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }


    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
  *//*      clients.jdbc(this.dataSource)
                .passwordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder());
  *//*
            clients.inMemory()
                    .withClient("first-client")
                    .secret(passwordEncoder().encode("noonewilleverguess"))
                    .scopes("resource:read")
                    .authorizedGrantTypes("authorization_code")
                    .redirectUris("http://localhost:8081/oauth/login/client-app");
    }
}*/
