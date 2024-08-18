package tech.inno.task8.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.inno.task8.entity.Payment;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Optional<Payment> findByUserIdAndTransactionIdAndPaymentAmount(Long userId, String transactionId, BigDecimal paymentAmount);
    List<Payment> findByDayLimitIdOrderByPaymentDateTime(Long dayLimitId);
}
