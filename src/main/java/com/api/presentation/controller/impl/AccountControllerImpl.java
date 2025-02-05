package com.api.presentation.controller.impl;

import com.api.application.dto.AccountBalanceDTO;
import com.api.application.dto.AccountDTO;
import com.api.application.dto.TransactionDTO;
import com.api.application.dto.request.AccountRequestDTO;
import com.api.application.dto.request.AccountTransactionRequestDTO;
import com.api.application.dto.request.ExtractRequestDTO;
import com.api.application.usecase.AccountUseCase;
import com.api.domain.exception.AccountException;
import com.api.presentation.controller.AccountController;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountControllerImpl implements AccountController {
    private final AccountUseCase accountUseCase;

    @PostMapping
    @Override
    public ResponseEntity<AccountDTO> create(@RequestBody @Valid AccountRequestDTO accountRequest) throws AccountException {
        return  ResponseEntity.status(HttpStatus.CREATED).body(accountUseCase.createAccount(accountRequest));
    }

    @GetMapping("/{number}/balance")
    @Override
    public ResponseEntity<AccountBalanceDTO> getAccountBalance(@PathVariable(name = "number") String number) throws AccountException {
        return ResponseEntity.ok(accountUseCase.getAccountBalance(number));
    }

    @PatchMapping("/{number}/activate")
    @Override
    public ResponseEntity<Void> activeAccount(@PathVariable(name = "number") String number) throws AccountException {
        accountUseCase.activateAccount(number);

        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{number}/inactivate")
    @Override
    public ResponseEntity<Void> inactivateAccount(@PathVariable(name = "number") String number) throws AccountException {
        accountUseCase.inactivateAccount(number);

        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{number}/unblocked")
    @Override
    public ResponseEntity<Void> unblockedAccount(@PathVariable(name = "number") String number) throws AccountException {
        accountUseCase.unblockedAccount(number);

        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{number}/blocked")
    @Override
    public ResponseEntity<Void> blockedAccount(@PathVariable(name = "number") String number) throws AccountException {
        accountUseCase.blockedAccount(number);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/{number}/transaction")
    @Override
    public ResponseEntity<Void> transaction(@PathVariable(name = "number") String number, @RequestBody @Valid AccountTransactionRequestDTO accountTransactionRequestDTO) throws AccountException {
        accountUseCase.transaction(number, accountTransactionRequestDTO);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/{number}/extract")
    @Override
    public ResponseEntity<List<TransactionDTO>> extract(@PathVariable(name = "number") String number, @RequestBody @Valid ExtractRequestDTO extractRequestDTO) {
        List<TransactionDTO> transactions = accountUseCase.extract(number, extractRequestDTO);

        return ResponseEntity.ok().body(transactions);
    }
}
