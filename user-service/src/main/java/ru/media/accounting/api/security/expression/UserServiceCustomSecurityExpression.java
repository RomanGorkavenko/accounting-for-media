package ru.media.accounting.api.security.expression;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.media.accounting.api.security.UserServiceJwtEntity;
import ru.media.accounting.exception.CustomAccessDeniedException;

/**
 * Сервис для проверки авторизованности пользователя, и его прав доступа.
 */
@Service("userServiceCustomSecurityExpression")
@RequiredArgsConstructor
public class UserServiceCustomSecurityExpression {

    /**
     * Проверка авторизации пользователя.
     * Проверяет есть ли у пользователя роль ROLE_ADMIN
     * или имя пользователя совпадает с именем авторизованного пользователя.
     * @param username - имя пользователя.
     * @return true - если пользователь имеет роль ROLE_ADMIN
     * или имя пользователя совпадает с именем авторизованного пользователя иначе false.
     * @throws CustomAccessDeniedException - если пользователь не авторизован.
     */
    public boolean canAccessUser(String username) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            throw new CustomAccessDeniedException();
        }

        UserServiceJwtEntity user = (UserServiceJwtEntity) authentication.getPrincipal();
        String currentUsername = user.getUsername();

        return currentUsername.equals(username) || hasAnyRole(authentication, "ROLE_ADMIN");

    }

    /**
     * Алиас для canAccessUser проверки авторизации пользователя.
     * @param email - email пользователя.
     * @return true - если пользователь имеет роль ROLE_ADMIN
     * или имя пользователя совпадает с именем авторизованного пользователя.
     * @throws CustomAccessDeniedException - если пользователь не авторизован.
     */
    public boolean canAccessUserByEmail(String email) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            throw new CustomAccessDeniedException();
        }

        UserServiceJwtEntity user = (UserServiceJwtEntity) authentication.getPrincipal();
        String currentEmail = user.getEmail();

        return currentEmail.equals(email) || hasAnyRole(authentication, "ROLE_ADMIN");

    }

    /**
     * Алиас для canAccessUser проверки авторизации пользователя.
     * @return true - если пользователь имеет роль ROLE_ADMIN.
     * @throws CustomAccessDeniedException - если пользователь не авторизован.
     */
    public boolean canAccessUserROLE_ADMIN() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            throw new CustomAccessDeniedException();
        }

        return hasAnyRole(authentication, "ROLE_ADMIN");

    }

    /**
     * Алиас для canAccessUserROLE_ADMIN проверки авторизации пользователя.
     * @param username - имя пользователя.
     * @return true - если пользователь имеет роль ROLE_ADMIN
     * или имя пользователя совпадает с именем авторизованного пользователя.
     * @throws CustomAccessDeniedException - если пользователь не авторизован.
     */
    public boolean canAccessUserROLE_ADMIN(String username) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            throw new CustomAccessDeniedException();
        }

        return hasAnyRole(authentication, "ROLE_ADMIN");

    }

    /**
     * Проверка наличия роли. Если роль есть, то вернет true. Если нет, то вернет false.
     * @param authentication - авторизация пользователя.
     * @param roles - роли.
     * @return true - если роль есть. Иначе false.
     */
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
