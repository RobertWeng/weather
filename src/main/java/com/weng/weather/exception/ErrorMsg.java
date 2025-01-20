package com.weng.weather.exception;

import lombok.Getter;

@Getter
public enum ErrorMsg {
    NOT_FOUND("error.notFound"),
    INTERNAL_SERVER_ERROR("error.internalServerError"),
    FORBIDDEN("error.forbidden");

    private final String message;

    ErrorMsg(String message) {
        this.message = message;
    }
}