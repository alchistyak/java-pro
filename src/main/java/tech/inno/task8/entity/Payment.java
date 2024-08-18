package tech.inno.task8.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import tech.inno.task8.enums.PaymentStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "payment_amount")
    private BigDecimal paymentAmount;
    @Column(name = "payment_datetime")
    private LocalDateTime paymentDateTime;
    @Column(name = "transaction_id")
    private String transactionId;
    @Column(name = "day_limit_id")
    private Long dayLimitId;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;
}
