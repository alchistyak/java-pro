package tech.inno.task8.service;

import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import tech.inno.task8.config.property.DefaultSettings;
import tech.inno.task8.dto.PaymentRequest;
import tech.inno.task8.dto.PaymentResponse;
import tech.inno.task8.entity.DayLimit;
import tech.inno.task8.entity.Payment;
import tech.inno.task8.enums.PaymentStatus;
import tech.inno.task8.exception.PaymentException;
import tech.inno.task8.repository.DayLimitRepository;
import tech.inno.task8.repository.PaymentRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class PaymentService {
    private final DayLimitRepository dayLimitRepository;
    private final PaymentRepository paymentRepository;
    private final DefaultSettings defaultSettings;

    public PaymentService(DayLimitRepository dayLimitRepository, PaymentRepository paymentRepository, DefaultSettings defaultSettings) {
        this.dayLimitRepository = dayLimitRepository;
        this.paymentRepository = paymentRepository;
        this.defaultSettings = defaultSettings;
    }

    // Проверка id пользователя на допустимое значение
    public void checkUser(Long userId) {
        if (userId < defaultSettings.getMinUserId() || userId > defaultSettings.getMaxUserId()) {
            throw new PaymentException("Недопустимый пользователь id=" + userId, HttpStatus.BAD_REQUEST);
        }
    }

    // Получение доступного лимита пользователя
    @Transactional
    public DayLimit getDayLimitByUserId(Long userId) {
        DayLimit dayLimit = new DayLimit();
        Optional<DayLimit> dayLimitOptional = dayLimitRepository.findByUserIdAndActive(userId, true);
        if (!dayLimitOptional.isPresent()) {
            dayLimit.setDateBegin(LocalDateTime.now());
            dayLimit.setUnusedLimit(BigDecimal.valueOf(10_000));
            dayLimit.setActive(true);
            dayLimit.setUserId(userId);
            dayLimit = dayLimitRepository.save(dayLimit);
        } else {
            dayLimit = dayLimitOptional.get();
        }
        return dayLimit;
    }

    // Списание доступного лимита на размер платежа
    @Transactional
    public DayLimit subtractLimit(DayLimit dayLimit, BigDecimal subtractAmount) {
        BigDecimal unusedLimit = dayLimit.getUnusedLimit().subtract(subtractAmount);
        if (unusedLimit.signum() == -1) {
            throw new PaymentException("Недосточно средств для выполнения операции на сумму " + subtractAmount + ". Доступный лимит: " + dayLimit.getUnusedLimit(), HttpStatus.BAD_REQUEST);
        }
        dayLimit.setUnusedLimit(dayLimit.getUnusedLimit().subtract(subtractAmount));
        dayLimit = dayLimitRepository.save(dayLimit);
        return dayLimit;
    }

    // Запись информации о платеже в БД
    @Transactional
    public Payment savePayment(DayLimit dayLimit, BigDecimal paymentAmount) {
        Payment payment = new Payment();
        payment.setPaymentAmount(paymentAmount);
        payment.setPaymentDateTime(LocalDateTime.now());
        payment.setUserId(dayLimit.getUserId());
        payment.setDayLimitId(dayLimit.getId());
        payment.setTransactionId(UUID.randomUUID().toString());
        payment.setPaymentStatus(PaymentStatus.PROCESSED);
        payment = paymentRepository.save(payment);
        return payment;
    }

    @Transactional
    public PaymentResponse processPayment(PaymentRequest paymentRequest) {
        System.out.println();
        checkUser(paymentRequest.userId());
        DayLimit dayLimit = getDayLimitByUserId(paymentRequest.userId());
        dayLimit = subtractLimit(dayLimit, paymentRequest.paymentAmount());
        Payment payment = savePayment(dayLimit, paymentRequest.paymentAmount());
        return new PaymentResponse(payment.getTransactionId(), payment.getPaymentDateTime(), payment.getPaymentAmount(), payment.getPaymentStatus(), dayLimit.getUnusedLimit());
    }
}
