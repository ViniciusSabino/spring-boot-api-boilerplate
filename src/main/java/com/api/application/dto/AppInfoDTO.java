package com.api.application.dto;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class AppInfoDTO {
    @Value("${info.app.name}")
    private String name;

    @Value("${info.app.description}")
    private   String description;

    @Value("${info.app.version}")
    private String version;

    @Value("${server.port}")
    private String port;
}
