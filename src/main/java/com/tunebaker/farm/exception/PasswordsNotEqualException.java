package com.tunebaker.farm.exception;

public class PasswordsNotEqualException extends RuntimeException {
    public PasswordsNotEqualException(String msg) {
        super(msg);
    }
}
