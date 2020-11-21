package com.sachin.security.multipleauthenticationprovider.security.filter;

import com.sachin.security.multipleauthenticationprovider.entities.Otp;
import com.sachin.security.multipleauthenticationprovider.repositories.OtpRepository;
import com.sachin.security.multipleauthenticationprovider.security.authentications.OtpAuthentication;
import com.sachin.security.multipleauthenticationprovider.security.authentications.UsernamePasswordAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;
import java.util.UUID;

@Component
public class UsernamePasswordAuthFilter extends OncePerRequestFilter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private OtpRepository otpRepository;

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return !request.getServletPath().equals("/login");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        // Step 1: username & password
        // step 2" username & otp
        String username = httpServletRequest.getHeader("username");
        String password = httpServletRequest.getHeader("password");
        String otp = httpServletRequest.getHeader("otp");
        if (otp == null) {
            //step 1
            UsernamePasswordAuthentication usernamePasswordAuthentication = new UsernamePasswordAuthentication(username, password);
            authenticationManager.authenticate(usernamePasswordAuthentication);
            // we generate an OTP
            String code = String.valueOf(new Random().nextInt(9999) + 1000);
            Otp otp1 = new Otp();
            otp1.setUsername(username);
            otp1.setOtp(code);
            otpRepository.save(otp1);
            httpServletResponse.setHeader("otp", code);

            //SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthentication);
        } else {
            // step 2
            OtpAuthentication otpAuthentication = new OtpAuthentication(username, otp);
            authenticationManager.authenticate(otpAuthentication);
            //SecurityContextHolder.getContext().setAuthentication(otpAuthentication);
            // we issue a token
            httpServletResponse.setHeader("Authorization", String.valueOf(UUID.randomUUID()));
        }
    }
}
