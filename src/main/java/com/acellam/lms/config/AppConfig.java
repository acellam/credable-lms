package com.acellam.lms.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;

@Getter
@Configuration
public class AppConfig {
    @Value("${server.port}")
    private String port;
    @Value("${spring.application.name}")
    private String appName;
}
