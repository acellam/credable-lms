package com.acellam.lms.crb;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import lombok.extern.slf4j.Slf4j;

import com.acellam.lms.config.AppConfig;
import com.acellam.lms.config.ConfigProperties;
import com.acellam.lms.crb.dtos.RegisterTransactionDto;
import com.acellam.lms.crb.dtos.RegisterTransactionResponseDto;

@Slf4j
@Service
public class ScoringServiceImp implements ScoringService {
    private final RestTemplate restTemplate;
    private final ConfigProperties config;
    private final AppConfig appConfig;
    public RegisterTransactionResponseDto registerTransactionResponseDto;

    public ScoringServiceImp(RestTemplate restTemplate, ConfigProperties config, AppConfig appConfig) {
        this.appConfig = appConfig;
        this.restTemplate = restTemplate;
        this.config = config;

        registerTransactionEndPoint();
    }

    @Override
    public boolean isEligibleForLoan(String customerNumber) {
        String initiateUrl = this.config.getInitiateQueryScoreUrl() + customerNumber;
        String queryUrl = this.config.getQueryScoreUrl() + customerNumber;

        // Initiate query
        HttpHeaders headers = new HttpHeaders();
        headers.set("client-token", this.registerTransactionResponseDto.token());

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> initiateResponse = restTemplate.exchange(initiateUrl, HttpMethod.GET, entity,
                String.class);

        String queryToken = initiateResponse.getBody();

        int retries = 3;
        int delay = 2000; // ms

        for (int i = 0; i < retries; i++) {
            try {
                Thread.sleep(delay);

                ResponseEntity<String> scoreResponse = restTemplate.exchange(
                        queryUrl + "/" + queryToken,
                        HttpMethod.GET, entity,
                        String.class);

                if (scoreResponse.getStatusCode() == HttpStatus.OK) {
                    return true;
                }

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        return false;
    }

    // Private methods
    private void registerTransactionEndPoint() {
        String ipAddress = null;
        try {
            ipAddress = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            throw new RuntimeException("Failed to get IP address", e);
        }

        String url = "http://" + ipAddress + ":" + appConfig.getPort() + "/api/v1/transactions";
        String endPoint = config.getTransactionCreateUrl();

        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        RegisterTransactionDto registerTransactionDto = new RegisterTransactionDto(
                url, appConfig.getAppName(), config.getUsername(), config.getPassword());

        HttpEntity<String> request = new HttpEntity<String>(registerTransactionDto.toString(), headers);

        try {
            ResponseEntity<RegisterTransactionResponseDto> response = restTemplate.postForEntity(endPoint, request,
                    RegisterTransactionResponseDto.class);

            this.registerTransactionResponseDto = response.getBody();
        } catch (Exception e) {
            log.error("Failed to register transaction endpoint ", e);
        }
    }
}
