package com.sachin.security.multipleauthenticationprovider.repositories;

import com.sachin.security.multipleauthenticationprovider.entities.Otp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OtpRepository extends JpaRepository<Otp, Integer> {
    Optional<Otp> findByUsername(String username);
}
