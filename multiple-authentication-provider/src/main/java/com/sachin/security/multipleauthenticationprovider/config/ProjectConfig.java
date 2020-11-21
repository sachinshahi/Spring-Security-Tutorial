package com.sachin.security.multipleauthenticationprovider.config;

import com.sachin.security.multipleauthenticationprovider.security.filter.UsernamePasswordAuthFilter;
import com.sachin.security.multipleauthenticationprovider.security.providers.OtpAuthenticationProvider;
import com.sachin.security.multipleauthenticationprovider.security.providers.UsernamePasswordAuthProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UsernamePasswordAuthProvider usernamePasswordAuthProvider;

    @Autowired
    private OtpAuthenticationProvider otpAuthenticationProvider;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Autowired
    private UsernamePasswordAuthFilter usernamePasswordAuthFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterAt(usernamePasswordAuthFilter, BasicAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(usernamePasswordAuthProvider)
                .authenticationProvider(otpAuthenticationProvider);
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
