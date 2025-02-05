package com.api.application.utils.interfaces;

import com.api.application.dto.TransactionDTO;
import com.api.infrastructure.entity.TransactionsEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TransactionsMapper {
    TransactionsMapper INSTANCE = Mappers.getMapper(TransactionsMapper.class);

    TransactionDTO entityToDTO(TransactionsEntity entity);
}
