package com.api.application.service;

import com.api.application.dto.AccountBalanceDTO;
import com.api.application.dto.AccountDTO;
import com.api.application.dto.request.AccountRequestDTO;
import com.api.application.dto.request.AccountTransactionRequestDTO;
import com.api.domain.exception.AccountException;

public interface AccountService {
    AccountDTO createAccount(AccountRequestDTO accountRequest) throws AccountException;

    AccountBalanceDTO getAccountBalance(String number) throws AccountException;

    void activateAccount(String number) throws AccountException;

    void inactivateAccount(String number) throws AccountException;

    void unblockedAccount(String number) throws AccountException;

    void blockedAccount(String number) throws AccountException;

    void deposit(String number, AccountTransactionRequestDTO accountTransactionRequest) throws AccountException;

    void withdraw(String number, AccountTransactionRequestDTO accountTransactionRequest) throws AccountException;
}
