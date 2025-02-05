package com.api.application.usecase;

import com.api.application.dto.AccountBalanceDTO;
import com.api.application.dto.AccountDTO;
import com.api.application.dto.TransactionDTO;
import com.api.application.dto.request.AccountRequestDTO;
import com.api.application.dto.request.AccountTransactionRequestDTO;
import com.api.application.dto.request.ExtractRequestDTO;
import com.api.domain.exception.AccountException;

import java.util.List;

public interface AccountUseCase {
    AccountDTO createAccount(AccountRequestDTO accountRequest) throws AccountException;

    AccountBalanceDTO getAccountBalance(String number) throws AccountException;

    void activateAccount(String number) throws AccountException;

    void inactivateAccount(String number) throws AccountException;

    void unblockedAccount(String number) throws AccountException;

    void blockedAccount(String number) throws AccountException;

    void transaction(String number, AccountTransactionRequestDTO accountTransactionRequest) throws AccountException;

    List<TransactionDTO> extract(String number, ExtractRequestDTO extractRequest);
}
