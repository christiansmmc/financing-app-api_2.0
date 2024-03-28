package com.christian.financingapp.domain.dto.transaction;

import com.christian.financingapp.domain.enumeration.TransactionType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TransactionToCreateDTO(
        @NotBlank String name,
        String description,
        @NotNull BigDecimal value,
        @NotNull TransactionType type,
        LocalDate date
) {
}
