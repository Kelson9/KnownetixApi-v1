package com.KnowNetix.Distributed.AI.Driven.multi.agent.system.exception;

public enum ErrorCodes {
    USER_NOT_FOUND("User Not found"),
    RESOURCE_NOT_FOUND("Resource not found"),
    ACCESS_DENIED("Access is denied"),
    ACCESS_FORBIDDEN("Access is forbidden"),
    VALIDATION_ERROR("Validation Error"),
    INT_SERVER_ERROR("Internal Server error"),
    PASSWORD_SIMILIARITY("Old password cannot be equal to new password"),
    PASSWORD_DIFFERENCE("Old password doesn't match"),
    RESET_TOKEN_EXPIRED("Reset token has expired"),
    PASSWORD_MISMATCH("Passwords do not match");

    private String message;

    ErrorCodes(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}