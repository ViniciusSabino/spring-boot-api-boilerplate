package com.api.application.usecase;

import com.api.application.dto.AccountHolderDTO;
import com.api.application.dto.request.AccountHolderRequestDTO;
import com.api.domain.exception.AccountHolderException;

public interface AccountHolderUseCase {

    AccountHolderDTO createAccountHolder(AccountHolderRequestDTO accountHolderRequestDTO) throws AccountHolderException;

    void deleteById(String id);

    void deleteByDocumentNumber(String documentNumber);

    AccountHolderDTO getByDocumentNumber(String documentNumber) throws AccountHolderException;
}
