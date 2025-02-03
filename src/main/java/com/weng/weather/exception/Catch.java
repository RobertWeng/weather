package com.weng.weather.exception;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.weng.weather.exception.Error.Code;
import com.weng.weather.util.MessageUtil;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@JsonIgnoreProperties({"cause", "stackTrace", "localizedMessage", "suppressed"})
public class Catch extends RuntimeException {

    private Code code;

    @JsonIgnore
    private HttpStatus httpStatus;

    public Catch(HttpStatus httpStatus, Code code, Error.Msg msg, Object... args) {
        this(httpStatus, code, msg.name(), args);
    }

    public Catch(HttpStatus httpStatus, Code code, String msg, Object... args) {
        super(MessageUtil.getMessage(msg, args));
        this.httpStatus = httpStatus;
        this.code = code;
    }

    public static Catch invalidRequest(Error.Msg msg, Object... args) {
        return new Catch(HttpStatus.BAD_REQUEST, Code.INVALID_REQUEST, msg, args);
    }

    public static Catch notFound(Object... args) {
        return new Catch(HttpStatus.NOT_FOUND, Code.NOT_FOUND, Error.Msg.NOT_FOUND, args);
    }

    public static Catch invalidState() {
        return new Catch(HttpStatus.BAD_REQUEST, Code.INVALID_STATE, Error.Msg.INVALID_STATE);
    }

    public static Catch internalServerError(String message, Object... args) {
        return new Catch(HttpStatus.INTERNAL_SERVER_ERROR, Code.INTERNAL_SERVER_ERROR, message, args);
    }
}
