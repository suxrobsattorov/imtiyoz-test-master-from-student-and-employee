package com.javacode.dto;

import lombok.Data;

@Data
public class TokenDTO {
    private String access_token;
    private String refresh_token;
}
