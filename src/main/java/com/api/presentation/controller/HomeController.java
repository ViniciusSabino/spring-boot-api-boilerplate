package com.api.presentation.controller;

import com.api.application.dto.AppInfoDTO;
import org.springframework.http.ResponseEntity;

public interface HomeController {
    ResponseEntity<AppInfoDTO> home();
}
