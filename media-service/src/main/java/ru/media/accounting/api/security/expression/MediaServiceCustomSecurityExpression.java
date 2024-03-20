package ru.media.accounting.api.security.expression;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.media.accounting.api.feign.UserFeignClient;
import ru.media.accounting.api.security.MediaServiceJwtEntity;

@Service("mediaServiceCustomSecurityExpression")
@RequiredArgsConstructor
public class MediaServiceCustomSecurityExpression {

    private final UserFeignClient userFeignClient;

    public boolean canAccessUser(String username) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        MediaServiceJwtEntity user = (MediaServiceJwtEntity) authentication.getPrincipal();
        String currentUsername = user.getUsername();

        return currentUsername.equals(username) || hasAnyRole(authentication, "ROLE_ADMIN");

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
