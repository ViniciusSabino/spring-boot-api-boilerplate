package com.api.application.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.br.CPF;

public record AccountRequestDTO(
        @Min(value = 1, message = "Agency number is invalid")
        @Max(value = 1, message = "Agency number is invalid")
        @Positive(message = "Invalid agency number")
        @JsonProperty("agencyNumber")
        Integer agencyNumber,

        @NotBlank(message = "Account holder document number is required")
        @CPF(message = "Account holder document number is invalid")
        String accountHolderDocumentNumber,

        @NotBlank(message = "Account number is required")
        @Pattern(regexp = "^\\d{9}-\\d{1}$", message = "Invalid account number")
        String number
)
{ }
