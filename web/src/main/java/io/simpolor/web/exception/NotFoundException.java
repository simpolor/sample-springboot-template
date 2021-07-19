package io.simpolor.web.exception;

import org.slf4j.helpers.MessageFormatter;

public class NotFoundException extends RuntimeException {

    public NotFoundException(){ }

    public NotFoundException(String format, Object... arguments){
        super(MessageFormatter.arrayFormat(format, arguments).getMessage());
    }

}
