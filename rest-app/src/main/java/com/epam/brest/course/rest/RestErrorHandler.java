package com.epam.brest.course.rest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataAccessException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class RestErrorHandler {
    private static final Logger LOGGER =LogManager.getLogger();


    @ExceptionHandler (DataAccessException.class)
    @ResponseStatus (HttpStatus.NOT_FOUND)
    public @ResponseBody String handlerDataAccessException (DataAccessException e){
        LOGGER.debug("handlerDataAccessException");
        return "DataAccessException"+ e.getLocalizedMessage();
    }
}
