package ru.gb.spring.boot.aop;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(TimerProperties.class)
@ConditionalOnProperty(value = "aspect.timer.enabled", havingValue = "true")
public class TimerAutoConfiguration {

    @Bean
    TimerAspect timerAspect(TimerProperties properties) {
        return new TimerAspect(properties);
    }
}
