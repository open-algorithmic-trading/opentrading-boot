package dev.opentrading.core.boot;

/**
 * Core Bootstrap interface for OpenTrading platform.
 * Broker specific module implements each operations.
 */
public interface CoreBootstrap {

    /***
     * Logs into broker trading account.
     * @param <T> Return type could be as simple as a Boolean.
     * @return returns operation status.
     */
    <T> T login();

    /***
     * Fetches account margin.
     * @param <T> Return type could be as simple as a Boolean.
     * @return returns operation status.
     */
    <T> T margin();

    /***
     * Loads all instruments for defined configuration in application properties.
     * @param <T> Return type could be as simple as a Boolean.
     * @return returns operation status.
     */
    <T> T instruments();

    /***
     * Filters trading instruments.
     * @param <T> Return type could be as simple as a Boolean.
     * @return returns operation status.
     */
    <T> T tradingInstruments();

    /***
     * Initializes WebSocket.
     * @param <T> Return type could be as simple as a Boolean.
     * @return returns operation status.
     */
    <T> T initWebsocket();

    /***
     * Streams ticks of stocks over WebSocket.
     * @param <T> Return type could be as simple as a Boolean.
     * @return returns operation status.
     */
    <T> T streamTicks();

    /***
     * Schedules reading ticks of stocks at regular intervals.
     * @param <T> Return type could be as simple as a Boolean.
     * @return returns operation status.
     */
    <T> T scheduleTicksReading();

    /***
     * Schedules ticks aggregator (bars/candlesticks)
     * @param <T> Return type could be as simple as a Boolean.
     * @return returns operation status.
     */
    <T> T scheduleTicksAggregator();

    /***
     * Initializes market data simulator.
     * @param <T> Return type could be as simple as a Boolean.
     * @return returns operation status.
     */
    <T> T initSimulator();
}
