package io.simpolor.api.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ApplicationException extends RuntimeException {

    Integer code;

    String message;

    public ApplicationException(ExceptionType exceptionType){

        super(exceptionType.getMessage());

        this.code = exceptionType.getCode();
        this.message = exceptionType.getMessage();
    }

    public ApplicationException(ExceptionType exceptionType, String message){

        super(message);

        this.code = exceptionType.getCode();
        this.message = message;
    }

    public ApplicationException(Integer code, String message){

        super(message);

        this.code = code;
        this.message = message;
    }
}
