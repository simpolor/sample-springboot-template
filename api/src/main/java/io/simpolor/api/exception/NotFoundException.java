package io.simpolor.api.exception;

import org.slf4j.helpers.MessageFormatter;

public class NotFoundException extends ApiException {

    public NotFoundException(String format, Object... arguments){
        super(ExceptionType.NOT_FOUND, MessageFormatter.arrayFormat(format, arguments).getMessage());
    }

}
