package com.udacity.jdnd.course3.critter.common;


import org.junit.platform.commons.util.ToStringBuilder;

public class PetException extends RuntimeException {
    private ErrorCode errorCode;
    private Object[] params;

    public PetException(ErrorCode errorCode, Object[] params) {
        super();
        this.errorCode = errorCode;
        this.params = params;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("errorCode", errorCode)
                .append("errorMessage", super.getMessage()).toString();
    }
}
