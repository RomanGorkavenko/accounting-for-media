package ru.media.accounting.api.security.expression;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.media.accounting.api.security.MediaServiceJwtEntity;
import ru.media.accounting.exception.CustomAccessDeniedException;
import ru.media.accounting.model.Media;
import ru.media.accounting.service.MediaService;

import java.util.Objects;

@Service("mediaServiceCustomSecurityExpression")
@RequiredArgsConstructor
public class MediaServiceCustomSecurityExpression {

    private final MediaService mediaService;

    public boolean canAccessUser(Long number) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            throw new CustomAccessDeniedException();
        }

        MediaServiceJwtEntity user = (MediaServiceJwtEntity) authentication.getPrincipal();
        Long currentUserID = user.getUser().getId();

        Media media = mediaService.findByNumber(number);
        Long userId = media.getUserId();

        return Objects.equals(currentUserID, userId) || hasAnyRole(authentication, "ROLE_ADMIN");

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
