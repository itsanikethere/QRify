package io.github.itsanikethere.qrify;

import io.github.itsanikethere.qrify.exception.QueryParameterException;
import io.github.itsanikethere.qrify.rest.dto.ErrorResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class QRifyExceptionHandler {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    private ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException() {
        String message = "Missing or invalid request body";
        int status = HttpStatus.BAD_REQUEST.value();

        ErrorResponse errorResponse = new ErrorResponse(message, status);
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleQueryParameterException(QueryParameterException e) {
        String message = e.getMessage();
        int status = HttpStatus.BAD_REQUEST.value();

        ErrorResponse errorResponse = new ErrorResponse(message, status);
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    private ResponseEntity<ErrorResponse> handleException(Exception e) {
        String message = e.getMessage();
        int status = HttpStatus.INTERNAL_SERVER_ERROR.value();

        ErrorResponse errorResponse = new ErrorResponse(message, status);
        return ResponseEntity.internalServerError().body(errorResponse);
    }

}
