package io.simpolor.api.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ApiException extends RuntimeException {

    Integer code;

    String message;

    public ApiException(ExceptionType exceptionType){

        super(exceptionType.getMessage());

        this.code = exceptionType.getCode();
        this.message = exceptionType.getMessage();
    }

    public ApiException(ExceptionType exceptionType, String message){

        super(message);

        this.code = exceptionType.getCode();
        this.message = message;
    }

    public ApiException(Integer code, String message){

        super(message);

        this.code = code;
        this.message = message;
    }
}
