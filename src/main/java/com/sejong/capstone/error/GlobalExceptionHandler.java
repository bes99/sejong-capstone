package com.sejong.capstone.error;

import com.sejong.capstone.hospital.NoNearHospitalFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<BaseResponse> handleException(MethodArgumentNotValidException e) {
        String message = e.getBindingResult().getFieldErrors().stream()
                .findFirst().map(fieldError ->
                        String.format("%s 오류. %s", fieldError.getField(), fieldError.getDefaultMessage()))
                .orElse(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new BaseResponse(message,400));
    }
    @ExceptionHandler(InvalidInputException.class)
    protected ResponseEntity<BaseResponse> invalidInputException(InvalidInputException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new BaseResponse(e.getMessage(),400));
    }

    @ExceptionHandler(NoNearHospitalFoundException.class)
    public ResponseEntity<String> handleNoNearHospitalFoundException(NoNearHospitalFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
