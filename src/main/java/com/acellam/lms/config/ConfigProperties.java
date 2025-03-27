package com.acellam.lms.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import lombok.Getter;

@Getter
@Configuration
@PropertySource("classpath:lms.properties")
public class ConfigProperties {
    @Value("${lms.username}")
    private String username;
    @Value("${lms.password}")
    private String password;
    @Value("${lms.crb.transaction.url.create}")
    private String transactionCreateUrl;
    @Value("${lms.crb.scoring.url.initiateQueryScore}")
    private String initiateQueryScoreUrl;
    @Value("${lms.crb.scoring.url.queryScore}")
    private String queryScoreUrl;
}
