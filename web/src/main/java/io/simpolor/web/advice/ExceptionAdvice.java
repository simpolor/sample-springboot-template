package io.simpolor.web.advice;

import io.simpolor.web.exception.ApplicationException;
import io.simpolor.web.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class ExceptionAdvice {

    private final HttpServletRequest request;

    @ExceptionHandler(ApplicationException.class)
    public ModelAndView of(ApplicationException e, RedirectAttributes ra) {

        log.warn("ApplicationException : {}", e.getMessage());

        ra.addFlashAttribute("return_message", e.getMessage());

        ModelAndView mav = new ModelAndView();

        String referer = request.getHeader("REFERER");
        if(Objects.nonNull(referer)){
            mav.setViewName("redirect:"+referer);
        }else{
            mav.setViewName("redirect:/");
        }

        return mav;
    }

    /**
     * 특정 오류에 대한 처리를 할 경우 별로의 Exception 을 추가
     * @param e
     * @return
     */
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView of(NotFoundException e) {

        String requestUri = request.getRequestURI();

        log.warn("NotFoundException : {}", requestUri);

        ModelAndView mav = new ModelAndView();
        mav.setViewName("/error/404");
        return mav;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView of(Exception e) {

        log.error("ServiceException : {}", e.getMessage(), e);

        ModelAndView mav = new ModelAndView();
        mav.setViewName("/error/500");
        return mav;
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
    public ModelAndView ofWrongRequestException(Exception e) {

        log.warn("WrongRequestException : {}", e.getMessage(), e);

        ModelAndView mav = new ModelAndView();
        mav.setViewName("/error/400");
        return mav;

    }
}
