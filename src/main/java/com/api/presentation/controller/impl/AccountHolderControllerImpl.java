package com.api.presentation.controller.impl;

import com.api.application.dto.AccountHolderDTO;
import com.api.application.dto.request.AccountHolderRequestDTO;
import com.api.application.usecase.AccountHolderUseCase;
import com.api.domain.exception.AccountHolderException;
import com.api.presentation.controller.AccountHolderController;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account-holder")
@RequiredArgsConstructor
public class AccountHolderControllerImpl implements AccountHolderController {
    private final AccountHolderUseCase accountHolderUseCase;

    @PostMapping
    @Override
    public ResponseEntity<AccountHolderDTO> create(@RequestBody @Valid AccountHolderRequestDTO accountHolderRequestDTO) throws AccountHolderException {
        AccountHolderDTO accountHolder = accountHolderUseCase.createAccountHolder(accountHolderRequestDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(accountHolder);
    }

    @DeleteMapping("/{accountHolderId}")
    @Override
    public ResponseEntity<Void> delete(@PathVariable(name = "accountHolderId") String id) {
       accountHolderUseCase.deleteById(id);

       return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete")
    @Override
    public ResponseEntity<Void> deleteByDocumentNumber(@RequestParam @Valid String documentNumber) {
        accountHolderUseCase.deleteByDocumentNumber(documentNumber);

        return ResponseEntity.ok().build();
    }

    @GetMapping("{documentNumber}")
    @Override
    public ResponseEntity<AccountHolderDTO> getByDocumentNumber(@PathVariable("documentNumber") String documentNumber) throws AccountHolderException {
        AccountHolderDTO accountHolder = accountHolderUseCase.getByDocumentNumber(documentNumber);

        return ResponseEntity.ok().body(accountHolder);
    }
}
