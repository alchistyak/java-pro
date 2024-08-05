package tech.inno.payment.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "integrations.executors")
public class ExecutorProperties {
    private final ExecutorPaymentsRestTemplate executorPaymentsProperties;

    @ConstructorBinding
    public ExecutorProperties(ExecutorPaymentsRestTemplate executorPaymentsProperties) {
        this.executorPaymentsProperties = executorPaymentsProperties;
    }

    public ExecutorPaymentsRestTemplate getExecutorProperties() {
        return executorPaymentsProperties;
    }
}
