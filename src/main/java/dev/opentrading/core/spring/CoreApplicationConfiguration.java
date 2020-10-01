package dev.opentrading.core.spring;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(CoreApplicationProperties.class)
@EnableAutoConfiguration
public class CoreApplicationConfiguration {
}
