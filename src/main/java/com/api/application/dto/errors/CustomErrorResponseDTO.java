package com.api.application.dto.errors;

import com.api.domain.enums.ErrorTypeEnum;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CustomErrorResponseDTO {
    private int status;

    private String message;

    private ErrorTypeEnum type;

    private List<FieldErrorDTO> errors;

    public CustomErrorResponseDTO() {
        errors = new ArrayList<>();
    }
}
