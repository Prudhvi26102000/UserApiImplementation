package com.DPDzero.responses;

import lombok.Data;

@Data
public class JwtAuthResponse {
    private String token;
    private long expire_in;
}
