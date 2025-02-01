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
@RequiredArgsConstructor // TODO: Relembrar o que faz
public class HomeControllerImp implements HomeController {
    private final AppInfoDTO info;

    @GetMapping("/")
    public ResponseEntity<AppInfoDTO> home() {
        return ResponseEntity.ok(info);
    }
}
