package com.christian.financingapp.controller;

import com.christian.financingapp.domain.dto.transaction.TransactionIdDTO;
import com.christian.financingapp.domain.dto.transaction.TransactionToCreateDTO;
import com.christian.financingapp.domain.dto.transaction.TransactionToGetDTO;
import com.christian.financingapp.domain.vm.TransactionDateResponseVM;
import com.christian.financingapp.domain.vm.TransactionSummaryVM;
import com.christian.financingapp.facade.TransactionFacade;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionFacade facade;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TransactionIdDTO create(@Valid @RequestBody TransactionToCreateDTO dto) {
        return facade.create(dto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TransactionToGetDTO> findAll(@RequestParam(name = "yearMonth") String yearMonthStr) {
        return facade.findAllByDate(yearMonthStr);
    }

    @GetMapping("/transaction-months")
    @ResponseStatus(HttpStatus.OK)
    public List<TransactionDateResponseVM> findTransactionMonths() {
        return facade.findTransactionMonths();
    }

    @GetMapping("/summary")
    @ResponseStatus(HttpStatus.OK)
    public TransactionSummaryVM getSummary(@RequestParam(name = "yearMonth") String yearMonthStr) {
        return facade.getSummary(yearMonthStr);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        facade.delete(id);
    }

}
