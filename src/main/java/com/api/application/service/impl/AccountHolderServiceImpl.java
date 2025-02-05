package com.api.application.service.impl;

import com.api.application.dto.AccountHolderDTO;
import com.api.application.dto.request.AccountHolderRequestDTO;
import com.api.application.service.AccountHolderService;
import com.api.application.utils.MapperUtils;
import com.api.domain.enums.ErrorTypeEnum;
import com.api.domain.exception.AccountHolderException;
import com.api.domain.utils.FieldUtil;
import com.api.infrastructure.entity.AccountHolderEntity;
import com.api.infrastructure.repository.AccountHolderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AccountHolderServiceImpl implements AccountHolderService {
    private final AccountHolderRepository repository;

    @Override
    public AccountHolderDTO getAccountHolderByDocumentNumber(String documentNumber) {
        AccountHolderEntity entity = repository.findByDocumentNumber(FieldUtil.cleanDocumentNumber(documentNumber));

        return MapperUtils.mapAccountHolderEntityToDTO(entity);
    }

    @Override
    public AccountHolderDTO createAccountHolder(AccountHolderRequestDTO accountHolderRequest) throws AccountHolderException {
        AccountHolderDTO existingAccountHolder = this.getAccountHolderByDocumentNumber(accountHolderRequest.documentNumber());

        if(Objects.nonNull(existingAccountHolder)) {
            throw new AccountHolderException("Account holder already exists", HttpStatus.BAD_REQUEST, ErrorTypeEnum.ACCOUNT_HOLDER_ALREADY_EXISTS);
        }

        AccountHolderEntity entity = new AccountHolderEntity();

        entity.setName(accountHolderRequest.name());
        entity.setDocumentNumber(accountHolderRequest.documentNumber());

        entity.setDocumentNumber(FieldUtil.cleanDocumentNumber(entity.getDocumentNumber()));

        AccountHolderEntity savedAccountHolder = repository.save(entity);

        return MapperUtils.mapAccountHolderEntityToDTO(savedAccountHolder);
    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(Long.valueOf(id));
    }

    @Override
    public void deleteByDocumentNumber(String documentNumber) {
        AccountHolderEntity entity = repository.findByDocumentNumber(FieldUtil.cleanDocumentNumber(documentNumber));

        repository.delete(entity);
    }

    @Override
    public AccountHolderDTO getByDocumentNumber(String documentNumber) throws AccountHolderException {
        AccountHolderEntity entity = repository.findByDocumentNumber(FieldUtil.cleanDocumentNumber(documentNumber));

        if(Objects.isNull(entity)) {
            throw new AccountHolderException("Account holder not found", HttpStatus.NOT_FOUND, ErrorTypeEnum.ACCOUNT_HOLDER_NOT_FOUND);
        }

        return MapperUtils.mapAccountHolderEntityToDTO(entity);
    }
}
