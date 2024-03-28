package com.christian.financingapp.repository;

import com.christian.financingapp.domain.Transaction;
import com.christian.financingapp.domain.User;
import com.christian.financingapp.domain.vm.TransactionDateVM;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findAllByUserAndDateIsBetween(
            User user,
            LocalDate initialDate,
            LocalDate endDate
    );

    @Query("SELECT new com.christian.financingapp.domain.vm.TransactionDateVM(function('YEAR', t.date), function('MONTH', t.date)) " +
            "FROM transaction t " +
            "WHERE t.date IS NOT NULL " +
            "AND t.user = :user " +
            "GROUP BY function('YEAR', t.date), function('MONTH', t.date) " +
            "ORDER BY function('YEAR', t.date) ASC, function('MONTH', t.date) ASC")
    List<TransactionDateVM> findTransactionMonths(User user);

    Optional<Transaction> findByIdAndUser(Long id, User user);
}
