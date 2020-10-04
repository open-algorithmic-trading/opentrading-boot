package dev.opentrading.core.spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.PreDestroy;

@Slf4j
@SpringBootApplication
public class CoreApplication {

    public static void main(String[] args) {
        System.exit(SpringApplication.exit(SpringApplication.run(CoreApplication.class, args)));
        log.info("Started OpenTrading application!");
    }

    @PreDestroy
    public void destroy() {
        log.info("Executing destroy()");
    }

}
