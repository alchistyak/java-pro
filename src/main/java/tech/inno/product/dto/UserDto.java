package tech.inno.product.dto;

import jakarta.validation.constraints.NotBlank;

public record UserDto(@NotBlank String username) {
}
