package com.christian.financingapp.domain.dto.tag;

import jakarta.validation.constraints.NotNull;

public record TagToCreateTransactionDTO(
        @NotNull Long id
) {
}