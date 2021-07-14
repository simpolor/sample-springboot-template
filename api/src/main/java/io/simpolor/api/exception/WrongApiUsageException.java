package io.simpolor.api.exception;

public class WrongApiUsageException extends ApplicationException{

    private static Long CODE = 1000L;
    private static String MESSAGE = "Wrong Api Usage";

    public WrongApiUsageException(){
        super(CODE, MESSAGE);
    }
}
