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
        coreBootstrap.login().thenAccept(loggedId -> {
            log.info("Logged in");

            coreBootstrap.getMargin().thenAccept(margin -> {
                log.info("Retrieved margin");

                coreBootstrap.getAllInstruments().thenAccept(instruments -> {
                    log.info("Loaded all instruments");

                    coreBootstrap.getTradingInstruments().thenAccept(tradingInstrument -> {
                        log.info("Filtered trading instruments");

                        coreBootstrap.initWebSocket().thenAccept(initWebsocket -> {
                            log.info("Initialized WebSocket");

                            coreBootstrap.streamTicks().thenAccept(ScheduledStreamingTicks -> {
                                log.info("Started streaming ticks");

                                coreBootstrap.scheduleTicksAggregator().thenAccept(success -> {
                                    log.info("Scheduled tick aggregator");

                                    log.info("Started CoreApplicationRunner.");
                                });
                            });
                        });
                    });
                });
            });
        });
    }
}
