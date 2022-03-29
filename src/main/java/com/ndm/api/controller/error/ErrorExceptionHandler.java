package com.ndm.api.controller.error;

import com.ndm.api.common.ConstantCommon;
import com.ndm.api.dto.Error;
import com.ndm.api.exception.DataNotFoundException;
import com.ndm.api.exception.DuplicateException;
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
    /**
     * This is a method to catch Invalid APIs Method exception
     * @return Error Object {400, "End Point Not Found."}
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(value = HttpStatus.METHOD_NOT_ALLOWED)
    @ResponseBody
    public Error handlerMethodNotAllowedException() {
        return Error.builder()
                    .code(HttpStatus.NOT_FOUND.value())
                    .message(ConstantCommon.PATH_NOT_FOUND)
                    .build();
    }

    /**
     * This is a method to catch Invalid Data access exception
     * @return Error Object {500, "Connection Refused: Connect."}
     */
    @ExceptionHandler(DataAccessException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public Error handlerDataAccessException() {
        return Error.builder()
                    .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .message(ConstantCommon.CONNECT_ERROR_MESSAGE)
                    .build();
    }

    /**
     * This is a method to catch Invalid parameter exception
     * @param ex InvalidParameterException.class
     * @return Error Object {400, ex.getMessage()}
     */
    @ExceptionHandler(InvalidParameterException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Error handlerInvalidParameterException(final InvalidParameterException ex) {
        return Error.builder()
                    .code(HttpStatus.BAD_REQUEST.value())
                    .message(ex.getMessage())
                    .build();
    }

    /**
     * This is a method to catch Data not found exception
     * @param ex DataNotFoundException.class
     * @return Error Object {404, ex.getMessage()}
     */
    @ExceptionHandler(DataNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ResponseBody
    public Error handlerDataNotFoundException(final DataNotFoundException ex) {
        return Error.builder()
                    .code(HttpStatus.NOT_FOUND.value())
                    .message(ex.getMessage())
                    .build();
    }

    /**
     * This is a method to catch Invalid duplicate exception
     * @param ex DuplicateException.class
     * @return Error Object {400, ex.getMessage()}
     */
    @ExceptionHandler(DuplicateException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Error handlerDuplicateException(final DuplicateException ex) {
        return Error.builder()
                    .code(HttpStatus.BAD_REQUEST.value())
                    .message(ex.getMessage())
                    .build();
    }

    /**
     * This is a method to catch Invalid exception
     * @return Error Object {500, "Internal Server Error."}
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public Error handlerException() {
        return Error.builder()
                    .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .message(ConstantCommon.SERVER_ERROR_MESSAGE)
                    .build();
    }
}
