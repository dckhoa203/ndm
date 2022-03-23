package com.ndm.api.controller;

import com.ndm.api.dto.ErrorDTO;
import com.ndm.api.exception.DataNotFoundException;
import com.ndm.api.exception.InvalidParameterException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ErrorExceptionHandler {
    private static final String CONNECT_ERROR_MESSAGE = "Connection Refused: Connect";
    private static final String PATH_NOT_FOUND = "End Point Not Found";
    private static final String SERVER_ERROR_MESSAGE = "Internal Server Error";

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(value = HttpStatus.METHOD_NOT_ALLOWED)
    @ResponseBody
    public ErrorDTO handlerMethodNotAllowedException() {
        return ErrorDTO.builder()
                       .code(HttpStatus.NOT_FOUND.value())
                       .message(PATH_NOT_FOUND)
                       .build();
    }

    @ExceptionHandler(DataAccessException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorDTO handlerDataAccessException() {
        return ErrorDTO.builder()
                       .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                       .message(CONNECT_ERROR_MESSAGE)
                       .build();
    }

    @ExceptionHandler(InvalidParameterException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorDTO handlerInvalidParameterException(final InvalidParameterException ex) {
        return ErrorDTO.builder()
                       .code(HttpStatus.BAD_REQUEST.value())
                       .message(ex.getMessage())
                       .build();
    }

    @ExceptionHandler(DataNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorDTO handlerDataNotFoundException(final DataNotFoundException ex) {
        return ErrorDTO.builder()
                       .code(HttpStatus.NOT_FOUND.value())
                       .message(ex.getMessage())
                       .build();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorDTO handlerException() {
        return ErrorDTO.builder()
                       .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                       .message(SERVER_ERROR_MESSAGE)
                       .build();
    }
}
