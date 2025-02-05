package com.api.application.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;

public record AgencyRequestDTO(
        @Min(value = 1, message = "Invalid agency number")
        @Max(value = 1, message = "Invalid agency number")
        @Positive(message = "Invalid agency number")
        Integer number
)
{ }
