package com.etoak.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Et1908LoginException.class)
    public ModelAndView handleLoginException(Et1908LoginException e) {
        log.error(e.getMessage(), e);
        ModelAndView mv = new ModelAndView();
        mv.addObject("error", e.getMessage());
        mv.setViewName("login");
        return mv;
    }

}
