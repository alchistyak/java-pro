package tech.inno.payment.config.properties;

import java.time.Duration;

public class ExecutorPaymentsRestTemplate {
    private final String url;
    private final Duration connectTimeout;
    private final Duration readTimeout;

    public ExecutorPaymentsRestTemplate(String url, Duration connectTimeout, Duration readTimeout) {
        this.url = url;
        this.connectTimeout = connectTimeout;
        this.readTimeout = readTimeout;
    }

    public String getUrl() {
        return url;
    }

    public Duration getConnectTimeout() {
        return connectTimeout;
    }

    public Duration getReadTimeout() {
        return readTimeout;
    }
}
