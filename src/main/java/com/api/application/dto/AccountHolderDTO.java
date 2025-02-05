package com.api.application.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountHolderDTO {
    private Long id;

    private String name;
}
