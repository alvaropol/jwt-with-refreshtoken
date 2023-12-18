package com.salesianos.triana.dam.jwtwithrefreshtoken.security.jwt.refreshtoken.service;

import com.salesianos.triana.dam.jwtwithrefreshtoken.security.errorhandling.TokenRefreshException;
import com.salesianos.triana.dam.jwtwithrefreshtoken.security.jwt.refreshtoken.model.RefreshToken;
import com.salesianos.triana.dam.jwtwithrefreshtoken.security.jwt.refreshtoken.repository.RefreshTokenRepository;
import com.salesianos.triana.dam.jwtwithrefreshtoken.user.model.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    @Value("${jwt.refresh.duration}")
    private int durationInMinutes;

    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    public RefreshToken createRefreshToken(User user) {
        RefreshToken refreshToken = RefreshToken.builder()
                .user(user)
                .token(UUID.randomUUID().toString())
                .expiryDate(Instant.now().plusSeconds(durationInMinutes * 60L))
                .build();
        return refreshTokenRepository.save(refreshToken);
    }

    public RefreshToken verify(RefreshToken refreshToken) {

        if (refreshToken.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(refreshToken);
            throw new TokenRefreshException(refreshToken.getToken(), "refresh token expired");

        }

        return refreshToken;


    }

    @Transactional
    public void deleteByUser(User user) {
        refreshTokenRepository.deleteByUser(user);
    }
}