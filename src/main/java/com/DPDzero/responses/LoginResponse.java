package com.DPDzero.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {
    private String status;
    private String message;
    private JwtAuthResponse data;
}
