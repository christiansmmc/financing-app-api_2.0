package com.christian.financingapp.domain.vm;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionSummaryVM implements Serializable {

    String formattedDate;

    private LocalDate initialDate;

    private LocalDate lastDate;

    private BigDecimal totalOutcome;

    private BigDecimal totalIncome;

    private BigDecimal profit;
}
