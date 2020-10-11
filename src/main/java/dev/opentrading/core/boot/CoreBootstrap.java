package dev.opentrading.core.boot;

import javax.inject.Named;
import java.util.concurrent.CompletableFuture;

/**
 * Core Bootstrap interface for OpenTrading platform.
 * Broker specific module implements each operations.
 */
@Named
public interface CoreBootstrap {
    /**
     * Logs into broker trading account.
     * @param <T> Return type could be as simple as a Boolean.
     * @return returns operation status.
     */
    <T> CompletableFuture<T> login();

    /**
     * Fetches account margin.
     * @param <T> Return type could be as simple as a Boolean.
     * @return returns operation status.
     */
    <T> CompletableFuture<T> getMargin();

    /**
     * Loads all instruments for defined configuration in application properties.
     * @param <T> Return type could be as simple as a Boolean.
     * @return returns operation status.
     */
    <T> CompletableFuture<T> getAllInstruments();

    /**
     * Filters trading instruments.
     * @param <T> Return type could be as simple as a Boolean.
     * @return returns operation status.
     */
    <T> CompletableFuture<T> getTradingInstruments();

    /**
     * Initializes WebSocket.
     * @param <T> Return type could be as simple as a Boolean.
     * @return returns operation status.
     */
    <T> CompletableFuture<T> initWebSocket();

    /**
     * Streams ticks of stocks over WebSocket.
     * @param <T> Return type could be as simple as a Boolean.
     * @return returns operation status.
     */
    <T> CompletableFuture<T> streamTicks();

    /**
     * Schedules reading ticks of stocks at regular intervals.
     * @param <T> Return type could be as simple as a Boolean.
     * @return returns operation status.
     */
    <T> CompletableFuture<T> scheduleTicksReading();

    /**
     * Schedules ticks aggregator (bars/candlesticks)
     * @param <T> Return type could be as simple as a Boolean.
     * @return returns operation status.
     */
    <T> CompletableFuture<T> scheduleTicksAggregator();

    /**
     * Initializes market data simulator.
     * @param <T> Return type could be as simple as a Boolean.
     * @return returns operation status.
     */
    <T> CompletableFuture<T> initSimulator();
}
