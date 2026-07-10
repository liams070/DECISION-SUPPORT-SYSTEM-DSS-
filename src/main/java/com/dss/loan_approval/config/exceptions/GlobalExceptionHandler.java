package com.dss.loan_approval.config.exceptions;

import com.dss.loan_approval.config.util.AppCodeConstants;
import com.dss.loan_approval.config.util.BaseApiResponse;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Map;

@RestControllerAdvice

public class GlobalExceptionHandler {

    @ExceptionHandler(AppException.class)
    public ResponseEntity<BaseApiResponse<?>> handleAppException(AppException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ex.getApiResponse());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<BaseApiResponse<?>> handleAccessDenied(AccessDeniedException ex) {
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(ex.getApiResponse());
    }

    @ExceptionHandler(AuthenticateException.class)
    public ResponseEntity<BaseApiResponse<?>> handleAuthenticate(AuthenticateException ex) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(ex.getApiResponse());
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<BaseApiResponse<?>> handleNotFound(NotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getApiResponse());
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BaseApiResponse<?>> handleValidationException(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();

        BaseApiResponse<?> response = new BaseApiResponse<>(HttpStatus.BAD_REQUEST.value(), "FAILED", "Validation failed",
                Map.of("errors", errors)
        );

        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseApiResponse<?>> handleGenericException(Exception ex) {
        BaseApiResponse<?> response = new BaseApiResponse<>(
                HttpStatus.INTERNAL_SERVER_ERROR.value(), "ERROR", "An unexpected error occurred: " + ex.getMessage()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<BaseApiResponse<?>> handleDuplicateEntry(DataIntegrityViolationException ex) {
        BaseApiResponse<?> response = new BaseApiResponse<>(
                AppCodeConstants.FAILED_CODE,
                AppCodeConstants.FAILED_MSG,
                AppCodeConstants.SUCCESS_MSG
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

}
