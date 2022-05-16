package fr.mns.jee.projet.errors.controller;

import fr.mns.jee.projet.errors.ErrorResponse;
import fr.mns.jee.projet.errors.InvalidParameterException;
import fr.mns.jee.projet.errors.NotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@ControllerAdvice
public class RestControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value= { NotFoundException.class })
    protected ResponseEntity<Object> handleNotFound(NotFoundException ex, WebRequest request) {
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        ErrorResponse er = new ErrorResponse();
        er.setResponseCode(httpStatus.value());
        er.setErrorMessage(ex.getMessage());
        return handleExceptionInternal(ex, er, new HttpHeaders(), httpStatus, request);
    }

    @ExceptionHandler(value= { InvalidParameterException.class })
    protected ResponseEntity<Object> handleNotFound(InvalidParameterException ex, WebRequest request) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        ErrorResponse er = new ErrorResponse();
        er.setResponseCode(httpStatus.value());
        er.setErrorMessage(ex.getMessage());
        return handleExceptionInternal(ex, er, new HttpHeaders(), httpStatus, request);
    }

    @ExceptionHandler(value= { Exception.class })
    protected ResponseEntity<Object> handleOtherException(Exception ex, WebRequest request) {
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        ErrorResponse er = new ErrorResponse();
        er.setResponseCode(httpStatus.value());
        er.setErrorMessage(ex.getMessage());
        return handleExceptionInternal(ex, er, new HttpHeaders(), httpStatus, request);
    }
}
