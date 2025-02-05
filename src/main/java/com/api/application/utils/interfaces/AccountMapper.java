package com.api.application.utils.interfaces;

import com.api.application.dto.AccountDTO;
import com.api.infrastructure.entity.AccountEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AccountMapper {
    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    AccountDTO entityToDTO(AccountEntity entity);
}
