package com.example.demo.aspect;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExceptionAspect {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionAspect.class);

    @AfterThrowing(pointcut = "execution(* com.example.demo.controllers.*.*(..))", throwing = "exception")
    public ResponseEntity<Object> handleControllerException(Exception exception) {
        logger.error("Exception in method : " + exception.getMessage(), exception);
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
