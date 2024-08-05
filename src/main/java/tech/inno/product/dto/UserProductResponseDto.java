package tech.inno.product.dto;

import java.util.List;

public record UserProductResponseDto (UserResponseDto user, List<ProductResponseDto> products) {
}
