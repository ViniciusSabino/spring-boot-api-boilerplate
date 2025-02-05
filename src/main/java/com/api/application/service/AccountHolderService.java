package com.api.application.service;

import com.api.application.dto.AccountHolderDTO;
import com.api.application.dto.request.AccountHolderRequestDTO;
import com.api.domain.exception.AccountHolderException;

public interface AccountHolderService {
    AccountHolderDTO getAccountHolderByDocumentNumber(String documentNumber);

    AccountHolderDTO createAccountHolder(AccountHolderRequestDTO accountHolderRequest) throws AccountHolderException;

    void deleteById(String id);

    void deleteByDocumentNumber(String documentNumber);

    AccountHolderDTO getByDocumentNumber(String documentNumber) throws AccountHolderException;
}
