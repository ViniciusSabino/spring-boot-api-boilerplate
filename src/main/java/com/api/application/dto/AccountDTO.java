package com.api.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AccountDTO {
    private Long id;

    private Integer agencyNumber;

    private String accountHolderDocumentNumber;

    private String number;

    private Float balance;

    @JsonProperty("isActive")
    private boolean isActive;

    @JsonProperty("isUnblocked")
    private boolean isUnblocked;

    private Float dailyLimit;

    private String createdAt;
}
