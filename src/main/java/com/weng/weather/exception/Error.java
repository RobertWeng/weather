package com.weng.weather.exception;

public class Error {

    public enum Code {
        NOT_FOUND,
        INVALID_REQUEST,
        INVALID_STATE,
        INTERNAL_SERVER_ERROR,
    }

    public enum Msg {
        NOT_FOUND,
        INTERNAL_SERVER_ERROR,
        INVALID_STATE
    }
}
