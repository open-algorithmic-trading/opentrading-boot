package dev.opentrading.core.spring;

import dev.opentrading.core.boot.CoreBootstrap;
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

    private final CoreBootstrap coreBootstrap;

    protected CoreApplicationRunner(CoreApplicationProperties applicationProperties, CoreBootstrap coreBootstrap) {
        this.applicationProperties = applicationProperties;
        this.coreBootstrap = coreBootstrap;
    }

    @Override
    public void run(ApplicationArguments args) {
        log.info("CoreApplicationRunner(1) starting...");
        if (coreBootstrap.login()) {
            log.info("Logged in");
            if (coreBootstrap.getMargin()) {
                log.info("Retrieved margin");
                if (coreBootstrap.getAllInstruments()) {
                    log.info("Loaded all instruments");
                    if (coreBootstrap.getTradingInstruments()) {
                        log.info("Filtered trading instruments");
                        if (coreBootstrap.initSimulator()) {

                        }
                        if (coreBootstrap.scheduleTicksReading()) {
                            log.info("Scheduled ticksReading");
                            if (coreBootstrap.initWebSocket()) {
                                log.info("Initialized WebSocket");
                                if (coreBootstrap.streamTicks()) {
                                    log.info("Started streaming ticks");
                                    if (coreBootstrap.scheduleTicksAggregator()) {
                                        log.info("Scheduled ticks aggregator");
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

}
