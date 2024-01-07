package net.ausiasmarch.mobibus.exception;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import net.ausiasmarch.mobibus.bean.ErrorResponseBean;

public class AppExceptionHandler {
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<?> UnauthorizedException(UnauthorizedException ex, WebRequest request) {
        ErrorResponseBean errorDetails = new ErrorResponseBean(HttpStatus.UNAUTHORIZED.name(),
                ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> ResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        ErrorResponseBean errorDetails = new ErrorResponseBean(HttpStatus.NOT_FOUND.name(), ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> AppExceptionHandler(Exception ex, WebRequest request) {
        ErrorResponseBean errorDetails = new ErrorResponseBean(HttpStatus.INTERNAL_SERVER_ERROR.name(),
                ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
