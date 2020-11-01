package dev.opentrading.core.spring;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@Data
@ConfigurationProperties(prefix = "opentrading.core")
public class CoreProperties {
    Options options;
    MarketData marketData;
    TradingData tradingData;

    @Data
    public static class Options {
        private boolean streamTicks;
        private boolean streamTicksAggregator;
    }
    @Data
    public static class MarketData {
        private String openTime;
        private String closeTime;
    }
    @Data
    public static class TradingData {
        private String startTime;
        private String endTime;
        private List<String> instrumentGroups;
    }
}
