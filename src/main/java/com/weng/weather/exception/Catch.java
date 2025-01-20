package com.weng.weather.exception;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;

@Getter
public class Catch extends RuntimeException {

    @Autowired
    private MessageSource messageSource;

    @JsonIgnore
    private final HttpStatus status;
    private final ErrorCode code;

    public Catch(HttpStatus httpStatus, ErrorCode code, ErrorMsg msg, Object... args) {
        this(httpStatus, code, msg.getMessage());
    }

    private Catch(HttpStatus status, ErrorCode code, String msg) {
        super(msg);
        this.status = status;
        this.code = code;
    }

    public static Catch invalidRequest(ErrorMsg msg, Object... args) {
        return new Catch(HttpStatus.BAD_REQUEST, ErrorCode.INVALID_REQUEST, msg, args);
    }

    public static <T> Catch entityNotFound(Class<T> entity) {
        return new Catch(HttpStatus.NOT_FOUND, ErrorCode.NOT_FOUND, ErrorMsg.NOT_FOUND, entity.getSimpleName());
    }

    public static Catch internalServerError() {
        return new Catch(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.INTERNAL_SERVER_ERROR, ErrorMsg.INTERNAL_SERVER_ERROR);
    }

    public static Catch forbidden() {
        return new Catch(HttpStatus.FORBIDDEN, ErrorCode.FORBIDDEN, ErrorMsg.FORBIDDEN);
    }

}