package com.api.application.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record AccountTransactionRequestDTO(
        @NotBlank(message = "invalid transaction type")
        String type,

        @Min(1)
        @Positive(message = "Invalid transaction value")
        Float value
)
{ }
