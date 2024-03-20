package ru.gb.spring.boot.aop;

import lombok.Data;
import org.slf4j.event.Level;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("aspect.timer")
public class TimerProperties {

    /**
     * Уровень логирования
     */
    private Level logLevel = Level.ERROR;

    /**
     * Включить выключить timer
     */
    private boolean enabled = true;
}
