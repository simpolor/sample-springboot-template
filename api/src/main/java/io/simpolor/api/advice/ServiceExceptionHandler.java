package io.simpolor.api.advice;

import io.simpolor.api.exception.ApplicationException;
import io.simpolor.api.exception.ServiceException;
import io.simpolor.api.exception.WrongApiUsageException;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

/***
 * @RestControllerAdvice는 @ControllerAdvice+@ResponseBody의 역할을 한다.
 * @ControllerAdvice를 사용할 경우 ResponseEntity를 통해 status와 body를 지정해야 한다.
 */
@Slf4j
@RestControllerAdvice(annotations = WithServiceResponse.class)
public class ServiceExceptionHandler {

    @ExceptionHandler(ApplicationException.class)
    public ServiceResponse<Void> of(ApplicationException e) {

        log.info("Application exception : {}", e.getMessage(), e);

        return ServiceResponse.of(e);

    }

    /**
     * @ResponseStatus를 통해 응답코드를 결정할 수 있음
     * @param e
     * @return
     */
    // @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ServiceResponse<Void> of(Exception e) {

        log.error("Service exception : {}", e.getMessage(), e);

        return ServiceResponse.of(new ServiceException());

    }

    @ExceptionHandler(value = {
            MethodArgumentNotValidException.class,
            MissingServletRequestParameterException.class,
            HttpMessageNotReadableException.class,
            HttpMediaTypeNotSupportedException.class,
            HttpRequestMethodNotSupportedException.class,
            MethodArgumentTypeMismatchException.class,
            ConstraintViolationException.class,
            BindException.class})
    public ServiceResponse<Void> ofWrongApiUsageException(Exception e) {

        log.warn("WrongApiUsageException : {}", e.getMessage());

        return ServiceResponse.of(new WrongApiUsageException());

    }
}
