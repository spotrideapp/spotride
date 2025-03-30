package com.spotride.spotride.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Handler for exceptions.
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handle {@link EmailAlreadyTakenException}.
     *
     * @param exception {@link EmailAlreadyTakenException}
     * @return {@link ResponseEntity} for exception
     */
    @ExceptionHandler(EmailAlreadyTakenException.class)
    public ResponseEntity<ErrorResponse> handleEmailAlreadyTaken(EmailAlreadyTakenException exception) {
        var errorResponse = new ErrorResponse("error", exception.getMessage(), HttpStatus.CONFLICT.value());
        log.debug(errorResponse.message);
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    /**
     * Handle {@link UsernameAlreadyTakenException}.
     *
     * @param exception {@link UsernameAlreadyTakenException}
     * @return {@link ResponseEntity} for exception
     */
    @ExceptionHandler(UsernameAlreadyTakenException.class)
    public ResponseEntity<ErrorResponse> handleUsernameAlreadyTaken(UsernameAlreadyTakenException exception) {
        var errorResponse = new ErrorResponse("error", exception.getMessage(), HttpStatus.CONFLICT.value());
        log.error(errorResponse.message);
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    /**
     * Handle {@link AccessDeniedException}.
     *
     * @param exception {@link AccessDeniedException}
     * @return {@link ResponseEntity} for exception
     */
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> handleAccessDeniedException(AccessDeniedException exception) {
        var message = String.format("You do not have permission to access this resource %s", exception.getMessage());
        var errorResponse = new ErrorResponse("error", message, HttpStatus.FORBIDDEN.value());
        log.error(message);
        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
    }

    /**
     * Handle {@link NullPointerException}.
     *
     * @param exception {@link NullPointerException}
     * @return {@link ResponseEntity} for exception
     */
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ErrorResponse> handleNullPointer(NullPointerException exception) {
        var message = String.format("Null pointer exception: %s", exception.getMessage());
        var errorResponse = new ErrorResponse("error", message, HttpStatus.BAD_REQUEST.value());
        log.error(message);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handle {@link Exception}.
     *
     * @param exception {@link Exception}
     * @return {@link ResponseEntity} for exception
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneralException(Exception exception) {
        var message = String.format("Internal Server Error: %s", exception.getMessage());
        var errorResponse = new ErrorResponse("error", message, HttpStatus.INTERNAL_SERVER_ERROR.value());
        log.error(message);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Model for error response.
     *
     * @param status status
     * @param message error message
     * @param code error code
     */
    public record ErrorResponse(String status, String message, int code) {}
}
