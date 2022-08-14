package com.blackhorse.auction.exception;


public class BusinessException extends RuntimeException {
    private String code;
    private String message;

    public BusinessException(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public BusinessException(String message) {
        this.code = "amount_not_match";
        this.message = message;
    }
}
