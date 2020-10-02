package dev.opentrading.core.spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

@Slf4j
public abstract class CoreApplicationRunner implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) {
        log.info("CoreApplicationRunner started");
    }

    public abstract void shutdown();
}
