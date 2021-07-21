package io.simpolor.api.exception;

public class WrongApiUsageException extends ApiException {

    public WrongApiUsageException(){
        super(ExceptionType.WRONG_API_USAGE);
    }
}
