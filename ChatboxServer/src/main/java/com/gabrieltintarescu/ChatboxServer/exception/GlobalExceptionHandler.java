package com.gabrieltintarescu.ChatboxServer.exception;

import com.gabrieltintarescu.ChatboxServer.exception.errors.ResourceNotFoundException;
import com.gabrieltintarescu.ChatboxServer.exception.errors.UserAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

/**
 * @author Gabriel Tintarescu
 * @project ChatboxServer
 * @created 9/16/2022
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    //  Handle specific exceptions

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest request) {
        return new ResponseEntity(ErrorDetails.builder()
                .timestamp(new Date())
                .message("Resource not found")
                .details(exception.getMessage())
                .path(ErrorUtil.getPath(request))
                .build(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<?> handleResourceNotFoundException(UserAlreadyExistsException exception, WebRequest request) {
        return new ResponseEntity(ErrorDetails.builder()
                .timestamp(new Date())
                .message("User already exists")
                .details(exception.getMessage())
                .path(ErrorUtil.getPath(request))
                .build(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleResourceNotFoundException(MethodArgumentNotValidException exception, WebRequest request) {
        return new ResponseEntity(ErrorDetails.builder()
                .timestamp(new Date())
                .message("Invalid data")
                .details("The entered data did not pass validation. Please check and try again.")
                .path(ErrorUtil.getPath(request))
                .build(), HttpStatus.BAD_REQUEST);
    }

    // Handle global exceptions

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGlobalException(Exception exception, WebRequest request) {
        return new ResponseEntity(ErrorDetails.builder()
                .timestamp(new Date())
                .message("Generic error")
                .details(exception.getMessage().split(";")[0])
                .path(request.getDescription(false))
                .build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
