package tech.inno.payments.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import tech.inno.payments.config.properties.ExecutorProperties;
import tech.inno.payments.config.properties.ExecutorPaymentsRestTemplate;
import tech.inno.payments.exception.PaymentsResponseErrorHandler;

import java.time.Duration;

@Configuration
@EnableConfigurationProperties(ExecutorProperties.class)
public class ApplicationConfig {
    @Bean
    public RestTemplate executorPaymentsOld(
            ExecutorProperties executorProperties,
            PaymentsResponseErrorHandler errorHandler
    ) {
        ExecutorPaymentsRestTemplate executorPaymentsRestTemplate = executorProperties.getExecutorProperties();
        return new RestTemplateBuilder()
                .rootUri(executorPaymentsRestTemplate.getUrl())
                .setConnectTimeout(executorPaymentsRestTemplate.getConnectTimeout())
                .setReadTimeout(executorPaymentsRestTemplate.getReadTimeout())
                .errorHandler(errorHandler)
                .build();
    }
}
