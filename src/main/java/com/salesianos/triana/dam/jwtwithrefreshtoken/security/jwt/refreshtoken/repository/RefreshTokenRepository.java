package com.salesianos.triana.dam.jwtwithrefreshtoken.security.jwt.refreshtoken.repository;

import com.salesianos.triana.dam.jwtwithrefreshtoken.security.jwt.refreshtoken.model.RefreshToken;
import com.salesianos.triana.dam.jwtwithrefreshtoken.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.Optional;
import java.util.UUID;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, UUID> {

    Optional<RefreshToken> findByToken(String token);

    @Modifying
    int deleteByUser(User user);
}