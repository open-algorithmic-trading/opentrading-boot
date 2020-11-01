package dev.opentrading.core.spring;

import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(CoreProperties.class)
public class CoreApplicationConfiguration {
    @Bean
    public ExitCodeGenerator exitCodeGenerator() {
        return () -> -1;
    }
}
