package com.DPDzero.exception;

import com.DPDzero.config.AppConstants;
import com.DPDzero.responses.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ErrorsException.class)
    public ResponseEntity<ErrorResponse> ErrorHandler(ErrorsException ex){
        String message=ex.getMessage();
        String status=ex.getStatus();
        String code=ex.getCode();
        ErrorResponse errorResponse=new ErrorResponse(status,code,message);
        return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
    }
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResponse> InternalServerHandler(){
        ErrorResponse errorResponse=new ErrorResponse(AppConstants.status,"INTERNAL_SERVER_ERROR",AppConstants.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public ResponseEntity<ErrorResponse> UnAuthorizedHandler(){
        ErrorResponse errorResponse=new ErrorResponse(AppConstants.status,"AUTHORIZATION_ERROR",AppConstants.AUTHORIZATION_ERROR);
        return new ResponseEntity<>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
