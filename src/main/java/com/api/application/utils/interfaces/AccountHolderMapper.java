package com.api.application.utils.interfaces;

import com.api.application.dto.AccountHolderDTO;
import com.api.infrastructure.entity.AccountHolderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AccountHolderMapper {

    AccountHolderMapper INSTANCE = Mappers.getMapper(AccountHolderMapper.class);

    AccountHolderDTO entityToDTO(AccountHolderEntity entity);
}
