package com.DPDzero.requests;

import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    private String email;
    private String password;
    private String full_name;
    private int age;
    private String gender;
}
