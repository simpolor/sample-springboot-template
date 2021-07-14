package io.simpolor.api.advice;

import io.simpolor.api.exception.ApplicationException;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Value
public class ServiceResponse<T> {

    public static final Long SUCCESS = 0L;
    public static final String SUCCESS_MESSAGE = "Success";

    Long code;

    String message;

    T value;

    public static <T> ServiceResponse<T> of(T response) {

        return new ServiceResponse<>(SUCCESS, SUCCESS_MESSAGE, response);
    }

    public static ServiceResponse<Void> of(ApplicationException ae) {

        return new ServiceResponse<>(ae.getCode(), ae.getMessage(), null);
    }

}
