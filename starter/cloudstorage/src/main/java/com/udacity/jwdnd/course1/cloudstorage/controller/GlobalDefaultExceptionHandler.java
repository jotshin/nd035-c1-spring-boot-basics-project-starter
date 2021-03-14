package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.servlet.ModelAndView;

import javax.naming.AuthenticationException;
import java.io.IOException;

@ControllerAdvice
public class GlobalDefaultExceptionHandler {
    @ExceptionHandler(value = AuthenticationException.class)
    public ModelAndView handleAuthorizationException(AuthenticationException exception) {
        return modelAndView(exception);
    }

    @ExceptionHandler(value = IOException.class)
    public ModelAndView handleIOException(IOException exception) {
        return modelAndView(exception);
    }

    @ExceptionHandler(value = HttpStatusCodeException.class)
    public ModelAndView handleHttpStatusCodeException(HttpStatusCodeException exception) {
        return modelAndView(exception);
    }

    @ExceptionHandler(value = HttpServerErrorException.InternalServerError.class)
    public ModelAndView handleHttpServerErrorException(HttpServerErrorException exception) {
        return modelAndView(exception);
    }

    @ExceptionHandler(value = NullPointerException.class)
    public ModelAndView handleNullPointerException(NullPointerException exception) {
        return modelAndView(exception);
    }

    private ModelAndView modelAndView(Exception exception) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("updateFail", "OOPS! Please make sure your input is correct.");
        mav.setViewName("result");
        return mav;
    }
}

