package com.salesianos.triana.dam.jwtwithrefreshtoken.config;

import com.salesianos.triana.dam.jwtwithrefreshtoken.user.model.User;
import lombok.extern.java.Log;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.*;

import java.util.Optional;
import java.util.UUID;

@Log
public class AuditorAwareImpl implements AuditorAware<String> {
    /*
    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String usuarioDesconocido = "";

        if (authentication != null) {
            if (authentication.getPrincipal() instanceof User u) {
                return Optional.of(u)
                        .map(User::getId)
                        .map(UUID::toString);
            }

            usuarioDesconocido = authentication.toString();

        }

        log.info("Usuario desconocido " + usuarioDesconocido);
        return Optional.empty();



    }
    */

    // Otra versión del método, basado en la documentación de Spring Boot

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.ofNullable(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication)
                .filter(Authentication::isAuthenticated)
                .map(Authentication::getPrincipal)
                .map(User.class::cast)
                .map(User::getId)
                .map(UUID::toString);


    }

}


