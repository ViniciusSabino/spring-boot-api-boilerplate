package com.api.application.dto;

import lombok.Data;

@Data
public class AccountBalanceDTO {
    private AccountDTO account;

    private AccountHolderDTO accountHolder;
}
