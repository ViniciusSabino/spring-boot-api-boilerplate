package com.api.presentation.controller;

import com.api.application.dto.AccountBalanceDTO;
import com.api.application.dto.AccountDTO;
import com.api.application.dto.TransactionDTO;
import com.api.application.dto.request.AccountRequestDTO;
import com.api.application.dto.request.AccountTransactionRequestDTO;
import com.api.application.dto.request.ExtractRequestDTO;
import com.api.domain.exception.AccountException;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AccountController {
    ResponseEntity<AccountDTO> create(AccountRequestDTO accountRequest) throws AccountException;

    ResponseEntity<AccountBalanceDTO> getAccountBalance(String number) throws AccountException;

    ResponseEntity<Void> activeAccount(String number) throws AccountException;

    ResponseEntity<Void> inactivateAccount(String number) throws AccountException;

    ResponseEntity<Void> unblockedAccount(String number) throws AccountException;

    ResponseEntity<Void> blockedAccount(String number) throws AccountException;

    ResponseEntity<Void> transaction (String number, AccountTransactionRequestDTO accountTransactionRequestDTO) throws AccountException;

    ResponseEntity<List<TransactionDTO>> extract (String number, ExtractRequestDTO extractRequestDTO);
}
