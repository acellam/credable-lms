package com.acellam.lms.crb.dtos;

public record RegisterTransactionResponseDto(
                int id,
                String url,
                String name,
                String username,
                String password,
                String token) {

}
