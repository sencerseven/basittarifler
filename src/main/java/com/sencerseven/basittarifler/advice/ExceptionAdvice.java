package com.sencerseven.basittarifler.advice;

import com.sencerseven.basittarifler.exceptions.NotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(NotFoundException.class)
    public ModelAndView notFoundException(){
        ModelAndView modelAndView = new ModelAndView("error");

        return modelAndView;
    }

    @ExceptionHandler(NumberFormatException.class)
    public ModelAndView NumberFormatException(){
        ModelAndView modelAndView = new ModelAndView("error");

        return modelAndView;
    }
}
