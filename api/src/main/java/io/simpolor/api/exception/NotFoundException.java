package io.simpolor.api.exception;

public class NotFoundException extends ApplicationException {

    private static Long CODE = 2000L;
    private static String MESSAGE = "Not found";

    public NotFoundException(){
        super(CODE, MESSAGE);
    }

}
