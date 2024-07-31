package tech.inno.payments.dto;

public record UpdateBalanceDto(
        Long id, // id продукта
        Long summa, // сумма списания/зачисления
        String action // действие: "-" - списание, "+" - зачисление
) {
}
