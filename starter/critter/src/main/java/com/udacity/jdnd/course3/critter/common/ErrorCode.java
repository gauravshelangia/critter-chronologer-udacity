package com.udacity.jdnd.course3.critter.common;

public enum  ErrorCode {
    CUSTOMER_NOT_EXISTS("customer.not.exists");

    private String messageKey;

    ErrorCode(String messageKey) {
        this.messageKey = messageKey;
    }

    public String getCode() {
        return this.name();
    }

    public String getMessageKey() {
        return this.messageKey;
    }
}