package dev.opentrading.core.spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;

import javax.inject.Named;

/**
 * First runner
 */
@Slf4j
@Named
@Order(1)
public abstract class CoreApplicationRunner implements ApplicationRunner {

    private final CoreApplicationProperties applicationProperties;

    protected CoreApplicationRunner(CoreApplicationProperties applicationProperties) {
        this.applicationProperties = applicationProperties;
    }

    @Override
    public void run(ApplicationArguments args) {
        log.info("CoreApplicationRunner(1) started");
    }

}
