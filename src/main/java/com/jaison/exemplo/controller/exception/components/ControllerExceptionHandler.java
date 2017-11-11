package com.jaison.exemplo.controller.exception.components;

import com.jaison.exemplo.controller.exception.AccountNotFoundException;
import com.jaison.exemplo.controller.exception.InvalidAccountRequestException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    @ResponseStatus(HttpStatus.NOT_FOUND) // 404
    @ExceptionHandler(AccountNotFoundException.class)
    public void handleNotFound(AccountNotFoundException ex) {
        logger.error("Requested account not found");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST) // 400
    @ExceptionHandler(InvalidAccountRequestException.class)
    public void handleBadRequest(InvalidAccountRequestException ex) {
        logger.error("Invalid account supplied in request");
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // 500
    @ExceptionHandler(Exception.class)
    public void handleGeneralError(Exception ex) {
        logger.error("An error occurred procesing request" + ex);
    }
}
