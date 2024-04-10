package com.christian.financingapp.domain;

import com.christian.financingapp.domain.enumeration.TransactionType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Table(name = "transaction")
@Entity(name = "transaction")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @NotNull
    @Column(name = "value")
    private BigDecimal value;

    @NotNull
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private TransactionType type;

    @Column(name = "date")
    @Builder.Default
    private LocalDate date = LocalDate.now();

    @NotNull
    @ManyToOne
    private User user;

    @ManyToOne
    private Tag tag;
}
