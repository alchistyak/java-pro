package tech.inno.payment.controller;

import org.springframework.web.bind.annotation.*;
import tech.inno.payment.dto.BalanceDto;
import tech.inno.payment.dto.ProductDto;
import tech.inno.payment.dto.UpdateBalanceDto;
import tech.inno.payment.service.PaymentsService;

@RestController
@RequestMapping("/api/v1")
public class PaymentsController {
    private final PaymentsService paymentsService;

    public PaymentsController(PaymentsService paymentsService) {
        this.paymentsService = paymentsService;
    }

    @GetMapping("/find_product/")
    public ProductDto getProductById(@RequestParam(name = "productid") Long productId) {
        return paymentsService.getProductById(productId);
    }

    @GetMapping("/check_balance/")
    public BalanceDto checkBalance(@RequestParam(name = "productid") Long productId) {
        return paymentsService.checkBalance(productId);
    }

    @PutMapping("/update_balance/")
    public ProductDto incrementBalance(@RequestBody UpdateBalanceDto request) {
        return paymentsService.updateBalance(request);
    }
}
