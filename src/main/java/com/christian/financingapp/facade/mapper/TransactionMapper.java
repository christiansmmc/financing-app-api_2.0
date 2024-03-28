package com.christian.financingapp.facade.mapper;

import com.christian.financingapp.domain.Transaction;
import com.christian.financingapp.domain.dto.transaction.TransactionIdDTO;
import com.christian.financingapp.domain.dto.transaction.TransactionToCreateDTO;
import com.christian.financingapp.domain.dto.transaction.TransactionToGetDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface TransactionMapper {

    Transaction toEntity(TransactionToCreateDTO dto);

    TransactionIdDTO toIdDto(Transaction transaction);

    List<TransactionToGetDTO> toGetDtoList(List<Transaction> transactions);
}
