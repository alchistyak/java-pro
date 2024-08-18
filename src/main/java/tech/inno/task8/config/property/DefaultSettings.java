package tech.inno.task8.config.property;

import java.math.BigDecimal;

public class DefaultSettings {
    private final BigDecimal defaultLimit;
    private final Long minUserId;
    private final Long maxUserId;

    public DefaultSettings(BigDecimal defaultLimit, Long minUserId, Long maxUserId) {
        this.defaultLimit = defaultLimit;
        this.minUserId = minUserId;
        this.maxUserId = maxUserId;
    }

    public BigDecimal getDefaultLimit() {
        return defaultLimit;
    }

    public Long getMinUserId() {
        return minUserId;
    }

    public Long getMaxUserId() {
        return maxUserId;
    }
}
