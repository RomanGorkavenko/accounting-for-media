package ru.media.accounting.api.security.expression;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.media.accounting.api.security.UserServiceJwtEntity;
import ru.media.accounting.service.UserService;

import java.util.NoSuchElementException;

/**
 * Сервис для проверки авторизованности пользователя, и его прав доступа.
 */
@Service("userServiceCustomSecurityExpression")
@RequiredArgsConstructor
public class UserServiceCustomSecurityExpression {

    private final UserService userService;

    public boolean canAccessUserByUsername(String username) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            throw new NoSuchElementException("Пользователь с username = " + username + " не найден");
        }

        UserServiceJwtEntity user = (UserServiceJwtEntity) authentication.getPrincipal();
        String currentUsername = user.getUsername();

        return currentUsername.equals(username) || hasAnyRole(authentication, "ROLE_ADMIN");

    }

    public boolean canAccessUserByEmail(String email) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            throw new NoSuchElementException("Пользователь с email = " + email + " не найден");
        }

        UserServiceJwtEntity user = (UserServiceJwtEntity) authentication.getPrincipal();
        String currentEmail = user.getEmail();

        return currentEmail.equals(email) || hasAnyRole(authentication, "ROLE_ADMIN");

    }

    public boolean canAccessUserROLE_ADMIN(String username) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            throw new NoSuchElementException("Пользователь с username = " + username + " не найден");
        }

        return hasAnyRole(authentication, "ROLE_ADMIN");

    }

    public boolean canAccessUserROLE_ADMIN() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return hasAnyRole(authentication, "ROLE_ADMIN");

    }

    private boolean hasAnyRole(Authentication authentication, String... roles) {
        for (String role : roles){
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role);
            if (authentication.getAuthorities().contains(authority)){
                return true;
            }
        }
        return false;
    }

}
