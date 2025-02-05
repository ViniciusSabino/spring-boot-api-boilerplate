package com.api.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

public record AccountHolderRequestDTO(
        @NotBlank(message = "Name is required")
        String name,

        @CPF(message = "Document number is invalid")
        String documentNumber
)
{ }
