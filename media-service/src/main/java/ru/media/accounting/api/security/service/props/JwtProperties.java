package ru.media.accounting.api.security.service.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties("security.jwt")
public class JwtProperties {

    private String secret;
    private Long access;
    private Long refresh;

}