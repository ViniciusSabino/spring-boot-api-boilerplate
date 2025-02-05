package com.api.application.utils;

import com.api.application.dto.AccountDTO;
import com.api.application.dto.AccountHolderDTO;
import com.api.application.dto.AgencyDTO;
import com.api.application.dto.TransactionDTO;
import com.api.application.utils.interfaces.AccountHolderMapper;
import com.api.application.utils.interfaces.AccountMapper;
import com.api.application.utils.interfaces.TransactionsMapper;
import com.api.domain.enums.TransactionTypeEnum;
import com.api.domain.utils.FieldUtil;
import com.api.infrastructure.entity.AccountEntity;
import com.api.infrastructure.entity.AccountHolderEntity;
import com.api.infrastructure.entity.AgencyEntity;
import com.api.infrastructure.entity.TransactionsEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MapperUtils<D> {

    public AccountHolderDTO mapAccountHolderEntityToDTO(AccountHolderEntity entity) {
        return AccountHolderMapper.INSTANCE.entityToDTO(entity);
    }

    public AccountDTO mapAccountEntityToDTO(AccountEntity entity) {
        AccountDTO accountDTO = AccountMapper.INSTANCE.entityToDTO(entity);

        accountDTO.setActive(entity.isActive());
        accountDTO.setUnblocked(entity.isUnblocked());

        return accountDTO;
    }

    public TransactionDTO mapTransactionEntityToDTO(TransactionsEntity entity) {
        TransactionDTO transaction =  TransactionsMapper.INSTANCE.entityToDTO(entity);

        transaction.setType(TransactionTypeEnum.valueOf(entity.getTransactionType().getType()));

        return transaction;
    }
}
