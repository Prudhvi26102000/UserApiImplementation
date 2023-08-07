package com.DPDzero.responses;

import com.DPDzero.model.UserData;
import lombok.Data;

@Data
public class RegisterResponse {
    private String status;
    private String message;
    private UserData data;
}
