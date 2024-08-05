package tech.inno.payment.dto;

import java.math.BigDecimal;

public record ChangeBalanceDto(
        Long id, // id продукта
        BigDecimal summa, // сумма списания/зачисления
        String action // действие: "-" - списание, "+" - зачисление
) {
}
