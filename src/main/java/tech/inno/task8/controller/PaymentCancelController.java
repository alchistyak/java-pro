package tech.inno.task8.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.inno.task8.dto.PaymentCancelRequest;
import tech.inno.task8.dto.PaymentCancelResponse;
import tech.inno.task8.service.PaymentCancelService;

// Отмена платежа
// Формат запроса:
//        {
//        "userId": 30,
//        "transactionId": "924047a1-85b7-45c3-8c91-f78c19cfe4f",
//        "paymentAmount": 5000
//        }
// Формат ответа:
//        {
//        "transactionId": "fed6b6d7-2e85-4499-b6d1-4455c6da585a",
//        "paymentAmount": 8000,
//        "paymentDateTime": "2024-08-18T01:27:54.597025",
//        "paymentStatus": "CANCELED",
//        "message": "Транзакция успешно отменена. Лимит восстановлен. Доступный лимит 10000.00"
//        }

@RestController
@RequestMapping("/api/v1")
public class PaymentCancelController {
    private final PaymentCancelService paymentCancelService;

    public PaymentCancelController(PaymentCancelService paymentCancelService) {
        this.paymentCancelService = paymentCancelService;
    }

    @PostMapping("/payment/cancel/")
    public PaymentCancelResponse paymentCancel(@Valid @RequestBody PaymentCancelRequest paymentCancelRequest) {
        return paymentCancelService.paymentCancel(paymentCancelRequest);
    }
}
