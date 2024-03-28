package com.christian.financingapp.domain.dto;

import jakarta.validation.constraints.NotNull;

public record AuthenticationRequestDTO(
        @NotNull String email,
        @NotNull String password
) {
}
