package io.simpolor.api.exception;

public class ServiceException extends ApplicationException {

    public ServiceException(){
        super(ExceptionType.UNKNOWN);
    }

}
