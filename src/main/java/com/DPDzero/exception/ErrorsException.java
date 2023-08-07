package com.DPDzero.exception;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class ErrorsException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    String message;
    String status;
    String code;
    public ErrorsException(String status, String code, String message){
        this.status=status;
        this.code=code;
        this.message=message;
    }
}
