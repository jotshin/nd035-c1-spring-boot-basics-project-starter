package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
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

    private ModelAndView modelAndView(Exception exception) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("updateFail", exception.getMessage());
        mav.setViewName("result");
        return mav;
    }
}

