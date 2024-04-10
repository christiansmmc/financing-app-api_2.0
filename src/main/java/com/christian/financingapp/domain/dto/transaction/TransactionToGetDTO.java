package com.christian.financingapp.domain.dto.transaction;

import com.christian.financingapp.domain.dto.tag.TagDTO;
import com.christian.financingapp.domain.enumeration.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TransactionToGetDTO(
        Long id,
        String name,
        String description,
        BigDecimal value,
        TransactionType type,
        LocalDate date,
        TagDTO tag
) {
}
