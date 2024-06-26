package ru.media.accounting.api.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.media.accounting.model.Role;
import ru.media.accounting.model.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Представление User для Spring Security.
 * Предоставляет информацию о пользователе.
 */
@Data
@AllArgsConstructor
public class UserServiceJwtEntity implements UserDetails {

    private User user;

    /**
     * Преобразование роли в полномочия Spring Security.
     * @return список полномочий.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<Role> roles = user.getRoles();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        roles.stream()
                .map(Role::getTitle)
                .forEach(role -> authorities.add(new SimpleGrantedAuthority(role)));

        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    public String getEmail() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}