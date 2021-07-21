package io.simpolor.api.exception;

public class UnknownException extends ApiException {

    public UnknownException(){
        super(ExceptionType.UNKNOWN);
    }

}
