package io.simpolor.api.exception;

public class ServiceException extends ApplicationException {

    private static Long CODE = 9000L;
    private static String MESSAGE = "Service Error";

    public ServiceException(){
        super(CODE, MESSAGE);
    }

}
