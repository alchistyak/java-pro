package tech.inno.task8.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import tech.inno.task8.config.property.DefaultSettings;
import tech.inno.task8.config.property.Settings;

@Configuration
@EnableScheduling
@EnableConfigurationProperties(Settings.class)
public class ApplicationConfig {
    @Bean
    public DefaultSettings defaultSettings(Settings settings) {
        return settings.getLimitPaymentProperties();
    }
}
