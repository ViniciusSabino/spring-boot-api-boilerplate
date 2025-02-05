package com.api.application.dto;

import com.api.domain.enums.TransactionTypeEnum;
import lombok.Data;

import java.time.Instant;

@Data
public class TransactionDTO {
    private TransactionTypeEnum type;

    private Instant date;

    private Float value;
}
