package fr.family.bernadet.service.impl;

import java.security.Principal;
import java.util.Optional;

import org.keycloak.KeycloakPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import fr.family.bernadet.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    
    @Override
    public Optional<String> getCurrentUserLogin() {
        SecurityContext securityContext = SecurityContextHolder.getContext();

        return Optional.ofNullable(securityContext.getAuthentication())
                .map(authentication -> {
                    if (authentication.getPrincipal() instanceof KeycloakPrincipal) {
                        Principal principal = (Principal) authentication.getPrincipal();
                        return principal.getName();
                    }
                    return null;
                });
    }
}
