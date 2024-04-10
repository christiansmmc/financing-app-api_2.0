package com.christian.financingapp.service;

import com.christian.financingapp.domain.Tag;
import com.christian.financingapp.domain.Transaction;
import com.christian.financingapp.domain.User;
import com.christian.financingapp.domain.enumeration.TransactionType;
import com.christian.financingapp.domain.vm.TransactionDateResponseVM;
import com.christian.financingapp.domain.vm.TransactionDateVM;
import com.christian.financingapp.domain.vm.TransactionSummaryVM;
import com.christian.financingapp.exception.EntityNotFoundException;
import com.christian.financingapp.repository.TagRepository;
import com.christian.financingapp.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository repository;
    private final TagRepository tagRepository;
    private final UserService userService;

    public Transaction create(Transaction transaction) {
        User loggedUser = userService.getLoggedUser();

        LocalDate transactionDate = transaction.getDate() != null
                ? transaction.getDate()
                : LocalDate.now();

        if (transaction.getTag() != null) {
            Tag tag = tagRepository.findById(transaction.getTag().getId())
                    .orElseThrow();

            transaction.setTag(tag);
        }

        transaction.setUser(loggedUser);
        transaction.setDate(transactionDate);
        return repository.save(transaction);
    }

    public List<Transaction> findAllByLoggedUserAndDate(String yearMonthStr) {
        User loggedUser = userService.getLoggedUser();

        List<LocalDate> dates = this.getFirstAndLastDayFromMonthByYearMonthStr(yearMonthStr);

        return repository.findAllByUserAndDateIsBetween(
                loggedUser,
                dates.get(0),
                dates.get(1)
        );
    }

    private List<LocalDate> getFirstAndLastDayFromMonthByYearMonthStr(String yearMonthStr) {
        YearMonth yearMonth = YearMonth.parse(
                yearMonthStr,
                DateTimeFormatter.ofPattern("yyyy-MM")
        );

        LocalDate firstDayOfMonth = yearMonth.atDay(1);
        LocalDate lastDayOfMonth = yearMonth.atEndOfMonth();

        return List.of(firstDayOfMonth, lastDayOfMonth);
    }

    public List<TransactionDateResponseVM> findTransactionMonths() {
        User loggedUser = userService.getLoggedUser();
        List<TransactionDateVM> dates = repository.findTransactionMonths(loggedUser);

        return dates
                .stream()
                .map(date -> {
                    String yearMonth = String.format("%d-%02d", date.year(), date.month());
                    return new TransactionDateResponseVM(yearMonth, this.getFormattedDate(yearMonth));
                })
                .collect(Collectors.toList());
    }

    private String getFormattedDate(String yearMonth) {
        LocalDate date = LocalDate.parse(yearMonth + "-01");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy", new Locale("pt", "BR"));
        return date.format(formatter);
    }

    public void delete(Long id) {
        User loggedUser = userService.getLoggedUser();
        Optional<Transaction> transaction = repository.findByIdAndUser(id, loggedUser);

        transaction.ifPresentOrElse(repository::delete, () -> {
            throw new EntityNotFoundException(Transaction.class);
        });
    }

    public TransactionSummaryVM getSummary(String yearMonthStr) {
        List<Transaction> transactions = this.findAllByLoggedUserAndDate(yearMonthStr);
        List<LocalDate> dates = this.getFirstAndLastDayFromMonthByYearMonthStr(yearMonthStr);

        BigDecimal totalOutcome = transactions
                .stream()
                .filter(transaction -> TransactionType.OUTCOME.equals(transaction.getType()))
                .map(Transaction::getValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal totalIncome = transactions
                .stream()
                .filter(transaction -> TransactionType.INCOME.equals(transaction.getType()))
                .map(Transaction::getValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return TransactionSummaryVM
                .builder()
                .formattedDate(this.getFormattedDate(yearMonthStr))
                .initialDate(dates.get(0))
                .lastDate(dates.get(1))
                .totalOutcome(totalOutcome)
                .totalIncome(totalIncome)
                .profit(totalIncome.subtract(totalOutcome))
                .build();
    }
}
