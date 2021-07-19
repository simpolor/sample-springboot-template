package io.simpolor.api.exception;

import org.slf4j.helpers.MessageFormatter;

public class NotFoundException extends ApplicationException {

    private static Long CODE = 2000L;
    private static String MESSAGE = "Not found";

    public NotFoundException(){
        super(CODE, MESSAGE);
    }

    public NotFoundException(String format, Object... arguments){
        super(CODE, MessageFormatter.arrayFormat(format, arguments).getMessage());
    }

}
