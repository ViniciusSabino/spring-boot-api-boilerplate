package com.api.presentation.controller.impl;

import com.api.application.dto.AppInfoDTO;
import com.api.presentation.controller.HomeController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/")
@RestController
@RequiredArgsConstructor
public class HomeControllerImpl implements HomeController {
    private final AppInfoDTO info;

    @GetMapping
    @Override
    public ResponseEntity<AppInfoDTO> home() {
        return ResponseEntity.ok(info);
    }
}
