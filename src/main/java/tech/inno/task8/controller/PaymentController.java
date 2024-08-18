package tech.inno.task8.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.inno.task8.dto.PaymentRequest;
import tech.inno.task8.dto.PaymentResponse;
import tech.inno.task8.service.DayLimitService;
import tech.inno.task8.service.PaymentService;

// Проведение платежа
// Формат запроса:
//    {
//        "userId": 30,
//        "paymentAmount": 1000.00
//    }
// Формат ответа:
//    {
//        "transactionId": "f5b37477-7c1d-43b2-9390-7d481bf7d657",
//        "paymentDateTime": "2024-08-18T01:33:51.9514013",
//        "paymentAmount": 1000,
//        "paymentStatus": "PROCESSED",
//        "unusedLimit": 9000
//    }

@RestController
@RequestMapping("/api/v1")
public class PaymentController {
    private final PaymentService paymentService;
    private final DayLimitService dayLimitService;

    public PaymentController(PaymentService paymentService, DayLimitService dayLimitService) {
        this.paymentService = paymentService;
        this.dayLimitService = dayLimitService;
    }

    @PostMapping("/payment/")
    public PaymentResponse processPayment(@Valid @RequestBody PaymentRequest paymentRequest) throws InterruptedException {
        return paymentService.processPayment(paymentRequest);
    }
}
