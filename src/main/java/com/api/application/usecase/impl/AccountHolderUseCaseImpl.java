package com.api.application.usecase.impl;

import com.api.application.dto.AccountHolderDTO;
import com.api.application.dto.request.AccountHolderRequestDTO;
import com.api.application.service.AccountHolderService;
import com.api.application.usecase.AccountHolderUseCase;
import com.api.domain.exception.AccountHolderException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountHolderUseCaseImpl implements AccountHolderUseCase {

    private final AccountHolderService service;

    @Override
    public AccountHolderDTO createAccountHolder(AccountHolderRequestDTO accountHolderRequestDTO) throws AccountHolderException {
        return service.createAccountHolder(accountHolderRequestDTO);
    }

    @Override
    public void deleteById(String id) {
        service.deleteById(id);
    }

    @Override
    public void deleteByDocumentNumber(String documentNumber) {
        service.deleteByDocumentNumber(documentNumber);
    }

    @Override
    public AccountHolderDTO getByDocumentNumber(String documentNumber) throws AccountHolderException {
        return service.getByDocumentNumber(documentNumber);
    }
}
