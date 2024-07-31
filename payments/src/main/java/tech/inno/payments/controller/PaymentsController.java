package tech.inno.payments.controller;

import org.springframework.web.bind.annotation.*;
import tech.inno.payments.dto.BalanceDto;
import tech.inno.payments.dto.ProductDto;
import tech.inno.payments.dto.UpdateBalanceDto;
import tech.inno.payments.service.PaymentsService;

@RestController
@RequestMapping("/api/v1")
public class PaymentsController {
    private final PaymentsService paymentsService;

    public PaymentsController(PaymentsService paymentsService) {
        this.paymentsService = paymentsService;
    }

    @GetMapping("/get_product_by_id/")
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
