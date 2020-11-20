package com.hk.study.exception;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Jason
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandel {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public String mathException() {
        return "Error Exception";
    }

}
