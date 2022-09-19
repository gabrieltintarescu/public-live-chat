package com.gabrieltintarescu.ChatboxServer.exception;

import com.gabrieltintarescu.ChatboxServer.exception.errors.ResourceNotFoundException;
import com.gabrieltintarescu.ChatboxServer.exception.errors.UnauthorizedAccessException;
import com.gabrieltintarescu.ChatboxServer.exception.errors.UserAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Date;

/**
 * @author Gabriel Tintarescu
 * @project ChatboxServer
 * @created 9/16/2022
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    //  Handle specific exceptions

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest request) {
        return new ResponseEntity(ErrorDetails.builder()
                .timestamp(new Date())
                .code(0)
                .message("Resource not found")
                .details(exception.getMessage())
                .path(ErrorUtil.getPath(request))
                .build(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<?> handleUserAlreadyExistsException(UserAlreadyExistsException exception, WebRequest request) {
        return new ResponseEntity(ErrorDetails.builder()
                .timestamp(new Date())
                .code(0)
                .message("User already exists")
                .details(exception.getMessage())
                .path(ErrorUtil.getPath(request))
                .build(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception, WebRequest request) {
        return new ResponseEntity(ErrorDetails.builder()
                .timestamp(new Date())
                .code(0)
                .message("Invalid Data")
                .details("The entered data did not pass validation. Please check and try again.")
                .path(ErrorUtil.getPath(request))
                .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnauthorizedAccessException.class)
    public ResponseEntity<?> handleUnauthorizedAccessException(UnauthorizedAccessException exception, WebRequest request) {
        return new ResponseEntity(ErrorDetails.builder()
                .timestamp(new Date())
                .code(1)
                .message("Unauthorized Access")
                .details(exception.getMessage())
                .path(ErrorUtil.getPath(request))
                .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<?> handleNullPointerException(NullPointerException exception, WebRequest request) {
        return new ResponseEntity(ErrorDetails.builder()
                .timestamp(new Date())
                .code(0)
                .message("Generic error")
                .details(exception.getMessage().split(";")[0])
                .path(ErrorUtil.getPath(request))
                .build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    // 404 Errors

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<?> handleNoHandlerFound(NoHandlerFoundException exception, WebRequest request) {
        return new ResponseEntity(ErrorDetails.builder()
                .timestamp(new Date())
                .code(0)
                .message("Generic error")
                .details(exception.getLocalizedMessage())
                .path(ErrorUtil.getPath(request))
                .build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Handle global exceptions

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGlobalException(Exception exception, WebRequest request) {
        return new ResponseEntity(ErrorDetails.builder()
                .timestamp(new Date())
                .code(0)
                .message("Generic error")
                .details(exception.getMessage().split(";")[0])
                .path(ErrorUtil.getPath(request))
                .build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
