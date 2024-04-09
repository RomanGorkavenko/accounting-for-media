package ru.media.accounting.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Конфигурация сервера для OpenApi
 */
@Component
@Data
@ConfigurationProperties("serverurl")
public class PropertyServerOpenApi {

    /**
     * Адрес сервера.
     */
    private String url;

    /**
     * Описание сервера.
     */
    private String description;
}
