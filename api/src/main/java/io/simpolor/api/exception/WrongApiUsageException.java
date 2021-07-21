package io.simpolor.api.exception;

public class WrongApiUsageException extends ApplicationException{

    public WrongApiUsageException(){
        super(ExceptionType.WRONG_API_USAGE);
    }
}
