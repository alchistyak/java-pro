package tech.inno.task8.service;

import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import tech.inno.task8.dto.PaymentCancelRequest;
import tech.inno.task8.dto.PaymentCancelResponse;
import tech.inno.task8.entity.DayLimit;
import tech.inno.task8.entity.Payment;
import tech.inno.task8.enums.PaymentStatus;
import tech.inno.task8.exception.PaymentException;
import tech.inno.task8.repository.DayLimitRepository;
import tech.inno.task8.repository.PaymentRepository;

import java.math.BigDecimal;

@Service
public class PaymentCancelService {
    private final PaymentRepository paymentRepository;
    private final DayLimitRepository dayLimitRepository;

    public PaymentCancelService(PaymentRepository paymentRepository, DayLimitRepository dayLimitRepository) {
        this.paymentRepository = paymentRepository;
        this.dayLimitRepository = dayLimitRepository;
    }

    // Поиск платежа
    @Transactional
    public Payment findPayment(Long userId, String transactionId, BigDecimal paymentAmount) {
        Payment payment = paymentRepository.findByUserIdAndTransactionIdAndPaymentAmount(userId, transactionId, paymentAmount)
                .orElseThrow(() -> new PaymentException("Не найдена транзакция id=" + transactionId + " на сумму " + paymentAmount + " пользователя id=" + userId, HttpStatus.NOT_FOUND));
        return payment;
    }

    // Отмена платежа
    @Transactional
    public Payment cancelPayment(Payment payment) {
        if (payment.getPaymentStatus() == PaymentStatus.CANCELED) {
            throw new PaymentException("Транзакция id=" + payment.getTransactionId() + " на сумму " + payment.getPaymentAmount() + " уже отменена", HttpStatus.BAD_REQUEST);
        }
        payment.setPaymentStatus(PaymentStatus.CANCELED);
        return paymentRepository.save(payment);
    }

    // Восстановление лимита
    @Transactional
    public DayLimit restoreDayLimit(Payment payment) {
        Long dayLimitId = payment.getDayLimitId();
        DayLimit dayLimit = dayLimitRepository.findById(dayLimitId)
                .orElseThrow(() -> new PaymentException("Не найден лимит id=" + dayLimitId + ", в пределах которого была проведена транзакция id" + payment.getTransactionId(), HttpStatus.NOT_FOUND));
        // Если лимит уже не активен, то не восстанавливаем его
        if (dayLimit.getActive() == true) {
            BigDecimal currentUnusedLimit = dayLimit.getUnusedLimit();
            dayLimit.setUnusedLimit(currentUnusedLimit.add(payment.getPaymentAmount()));
            dayLimit = dayLimitRepository.save(dayLimit);
        }
        return dayLimit;
    }

    @Transactional
    public PaymentCancelResponse paymentCancel(PaymentCancelRequest paymentCancelRequest) {
        Payment payment = findPayment(paymentCancelRequest.userId(), paymentCancelRequest.transactionId(), paymentCancelRequest.paymentAmount());
        payment = cancelPayment(payment);
        DayLimit dayLimit = restoreDayLimit(payment);
        return new PaymentCancelResponse(
                payment.getTransactionId(),
                payment.getPaymentAmount(),
                payment.getPaymentDateTime(),
                payment.getPaymentStatus().name(),
                "Транзакция успешно отменена" + (dayLimit.getActive() ? (". Лимит восстановлен. Доступный лимит " + dayLimit.getUnusedLimit()) : ". Лимит неактивен, восстановление не требуется"));
    }
}
