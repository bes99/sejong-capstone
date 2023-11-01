package com.sejong.capstone.hospital;

public class NoNearHospitalFoundException extends RuntimeException {
    public NoNearHospitalFoundException(String message) {
        super(message);
    }
}
