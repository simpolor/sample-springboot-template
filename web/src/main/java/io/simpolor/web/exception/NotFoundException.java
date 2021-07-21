package io.simpolor.web.exception;

import org.slf4j.helpers.MessageFormatter;

public class NotFoundException extends ApplicationException {

    public NotFoundException(String format, Object... arguments){
        super(ExceptionType.NOT_FOUND, MessageFormatter.arrayFormat(format, arguments).getMessage());
    }

}
