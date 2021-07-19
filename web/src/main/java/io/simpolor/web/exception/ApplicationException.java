package io.simpolor.web.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.slf4j.helpers.MessageFormatter;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ApplicationException extends RuntimeException {

    ExceptionType exceptionType;

    public ApplicationException(ExceptionType exceptionType){
        super(exceptionType.getMessage());
        this.exceptionType = exceptionType;
    }

    public ApplicationException(ExceptionType exceptionType, String format, Object... arguments){
        super(MessageFormatter.arrayFormat(format, arguments).getMessage());
        this.exceptionType = exceptionType;
    }

    public ExceptionType getExceptionType(){
        return this.exceptionType;
    }
}
