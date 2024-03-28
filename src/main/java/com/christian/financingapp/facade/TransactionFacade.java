package com.christian.financingapp.facade;

import com.christian.financingapp.domain.Transaction;
import com.christian.financingapp.domain.dto.transaction.TransactionIdDTO;
import com.christian.financingapp.domain.dto.transaction.TransactionToCreateDTO;
import com.christian.financingapp.domain.dto.transaction.TransactionToGetDTO;
import com.christian.financingapp.domain.vm.TransactionDateResponseVM;
import com.christian.financingapp.domain.vm.TransactionSummaryVM;
import com.christian.financingapp.facade.mapper.TransactionMapper;
import com.christian.financingapp.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionFacade {

    private final TransactionMapper mapper;
    private final TransactionService service;

    @Transactional
    public TransactionIdDTO create(TransactionToCreateDTO dto) {
        Transaction transaction = mapper.toEntity(dto);
        return mapper.toIdDto(service.create(transaction));
    }

    @Transactional(readOnly = true)
    public List<TransactionToGetDTO> findAllByDate(String yearMonthStr) {
        return mapper.toGetDtoList(service.findAllByLoggedUserAndDate(yearMonthStr));
    }

    @Transactional(readOnly = true)
    public List<TransactionDateResponseVM> findTransactionMonths() {
        return service.findTransactionMonths();
    }

    @Transactional
    public void delete(Long id) {
        service.delete(id);
    }

    @Transactional(readOnly = true)
    public TransactionSummaryVM getSummary(String yearMonthStr) {
        return service.getSummary(yearMonthStr);
    }
}
