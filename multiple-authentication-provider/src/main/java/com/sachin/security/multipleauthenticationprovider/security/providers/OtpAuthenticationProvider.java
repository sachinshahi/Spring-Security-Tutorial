package com.sachin.security.multipleauthenticationprovider.security.providers;


import com.sachin.security.multipleauthenticationprovider.entities.Otp;
import com.sachin.security.multipleauthenticationprovider.entities.User;
import com.sachin.security.multipleauthenticationprovider.repositories.OtpRepository;
import com.sachin.security.multipleauthenticationprovider.security.authentications.OtpAuthentication;
import com.sachin.security.multipleauthenticationprovider.security.model.SecuirtyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class OtpAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private OtpRepository otpRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String otp = (String) authentication.getCredentials();
        Optional<Otp> o = otpRepository.findByUsername(username);
        if (o.isPresent()) {
            List<GrantedAuthority> authority = new ArrayList<>();
            authority.add(new GrantedAuthority() {
                @Override
                public String getAuthority() {
                    return "read";
                }
            });
            return new OtpAuthentication(username, otp, authority);
        }
        throw new BadCredentialsException(":(");
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return OtpAuthentication.class.equals(aClass);
    }
}
