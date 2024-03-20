package ru.media.accounting.api.security.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties("security.jwt")
public class MediaServiceJwtProperties {

    private String secret;
    private Long access;
    private Long refresh;

}
