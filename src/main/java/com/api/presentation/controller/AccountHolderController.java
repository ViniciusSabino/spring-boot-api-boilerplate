package com.api.presentation.controller;

import com.api.application.dto.AccountHolderDTO;
import com.api.application.dto.request.AccountHolderRequestDTO;
import com.api.domain.exception.AccountHolderException;
import org.springframework.http.ResponseEntity;

public interface AccountHolderController {
    ResponseEntity<AccountHolderDTO> create(AccountHolderRequestDTO accountHolderRequestDTO) throws AccountHolderException;

    ResponseEntity<Void> delete(String id);

    ResponseEntity<Void> deleteByDocumentNumber(String documentNumber);

    ResponseEntity<AccountHolderDTO> getByDocumentNumber(String documentNumber) throws AccountHolderException;
}
