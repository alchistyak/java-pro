package tech.inno.task8.config.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.def")
public class Settings {
    private final DefaultSettings limitPaymentProperties;

    public Settings(DefaultSettings limitPaymentProperties) {
        this.limitPaymentProperties = limitPaymentProperties;
    }

    public DefaultSettings getLimitPaymentProperties() {
        return limitPaymentProperties;
    }
}
