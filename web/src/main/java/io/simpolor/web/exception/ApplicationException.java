package io.simpolor.web.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.slf4j.helpers.MessageFormatter;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ApplicationException extends RuntimeException {

    Integer code;

    String message;

    public ApplicationException(ExceptionType exceptionType){
        super(exceptionType.getMessage());

        this.code = exceptionType.getCode();
        this.message = exceptionType.getMessage();;
    }

    public ApplicationException(ExceptionType exceptionType, String format, Object... arguments){
        super(MessageFormatter.arrayFormat(format, arguments).getMessage());

        this.code = exceptionType.getCode();
        this.message = MessageFormatter.arrayFormat(format, arguments).getMessage();
    }

    public ApplicationException(Integer code, String format, Object... arguments){
        super(MessageFormatter.arrayFormat(format, arguments).getMessage());

        this.code = code;
        this.message = MessageFormatter.arrayFormat(format, arguments).getMessage();
    }
}
