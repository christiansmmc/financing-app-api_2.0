package com.christian.financingapp.domain.dto.user;

import jakarta.validation.constraints.NotBlank;


public record UserToCreateDTO(
        @NotBlank String email,
        @NotBlank String password
) {
}
