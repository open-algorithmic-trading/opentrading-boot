package dev.opentrading.core.spring;

import dev.opentrading.core.boot.CoreBootstrap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;

import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * First runner
 */
@Slf4j
@Named
@Order(1)
public abstract class CoreApplicationRunner implements ApplicationRunner {
    private final CoreProperties coreProperties;

    private final CoreBootstrap coreBootstrap;

    protected CoreApplicationRunner(CoreProperties applicationProperties, CoreBootstrap coreBootstrap) {
        this.coreProperties = applicationProperties;
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

                        coreBootstrap.<Boolean> initWebSocket().thenApply(initialized -> {
                            log.info("Initialized WebSocket");

                            List<CompletableFuture<Boolean>> completableFutureList = new ArrayList<>();
                            if (coreProperties.getOptions().isStreamTicks()) {
                                completableFutureList.add((coreBootstrap.<Boolean> streamTicks()
                                        .thenApply(scheduled -> {
                                            log.info("Scheduled streaming ticks");
                                            return scheduled;
                                        })));
                            }
                            if (coreProperties.getOptions().isStreamTicksAggregator()) {
                                completableFutureList.add((coreBootstrap.<Boolean> scheduleTicksAggregator()
                                        .thenApply(scheduled -> {
                                            log.info("Scheduled ticks aggregator");
                                            return scheduled;
                                        })));
                            }
                            return initialized && completableFutureList.stream()
                                    .map(CompletableFuture::join)
                                    .collect(Collectors.toList())
                                    .stream()
                                    .reduce(true, Boolean::logicalAnd);
                        }).whenComplete((initialized, throwable) -> {
                            if (throwable != null) {
                                log.error("Exception occurred. exception={}", throwable.getCause().getMessage());
                            }
                            if (initialized) {
                                log.info("Started CoreApplicationRunner");
                            }
                        });
                    });
                });
            });
        });
    }
}
