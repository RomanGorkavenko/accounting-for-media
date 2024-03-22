package ru.media.accounting.api.security.expression;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.media.accounting.api.security.MediaServiceJwtEntity;
import ru.media.accounting.exception.CustomAccessDeniedException;

import java.util.NoSuchElementException;

@Service("mediaServiceCustomSecurityExpression")
@RequiredArgsConstructor
public class MediaServiceCustomSecurityExpression {

    public boolean canAccessUser(String username) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            throw new NoSuchElementException("Пользователь с username = " + username + " не найден");
        }

        MediaServiceJwtEntity user = (MediaServiceJwtEntity) authentication.getPrincipal();
        String currentUsername = user.getUsername();

        return currentUsername.equals(username) || hasAnyRole(authentication, "ROLE_ADMIN");

    }

    public boolean canAccessUserROLE_ADMIN() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            throw new CustomAccessDeniedException();
        }

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
