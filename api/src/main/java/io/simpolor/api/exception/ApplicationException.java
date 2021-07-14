package io.simpolor.api.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ApplicationException extends RuntimeException {

    Long code;

    String message;

    public ApplicationException(Long code, String message){

        super(message);

        this.code = code;
        this.message = message;
    }
}
