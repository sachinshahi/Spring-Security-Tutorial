package com.sachin.security.multipleauthenticationprovider.service;

import com.sachin.security.multipleauthenticationprovider.entities.User;
import com.sachin.security.multipleauthenticationprovider.repositories.UserRepository;
import com.sachin.security.multipleauthenticationprovider.security.model.SecuirtyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JpaUserDetailService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> o = userRepository.findByUsername(s);
        User u = o.orElseThrow(() -> new UsernameNotFoundException(":("));
        return new SecuirtyUser(u);
    }
}
