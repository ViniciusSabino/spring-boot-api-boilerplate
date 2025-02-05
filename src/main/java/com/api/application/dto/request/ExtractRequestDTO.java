package com.api.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record ExtractRequestDTO(
        @NotBlank(message = "start date is required")
        @Pattern(regexp = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])T([01]\\d|2[0-3]):([0-5]\\d):([0-5]\\d)([+-][01]\\d:?[0-5]\\d)$", message = "invalid start date")
        String startDate,

        @NotBlank(message = "end date is required")
        @Pattern(regexp = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])T([01]\\d|2[0-3]):([0-5]\\d):([0-5]\\d)([+-][01]\\d:?[0-5]\\d)$", message = "invalid end date")
        String endDate
)
{ }
