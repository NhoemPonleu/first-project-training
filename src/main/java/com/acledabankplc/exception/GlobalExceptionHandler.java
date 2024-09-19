package com.acledabankplc.exception;

import com.acledabankplc.baseResponse.BaseApi;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    // Handle Internal Server Error (500)
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleInternalServerError(RuntimeException ex, WebRequest request) {

        // Custom response body
        BaseApi<Object> response = BaseApi.builder()
                .message("An internal error occurred: " + ex.getMessage())
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .status(false)
                .timeStamp(LocalDateTime.now())
                .data(null)
                .build();

        // Return the custom response with status 500
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<?> handleAccessDeniedException(AccessDeniedException e) {
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body("Access Denied: " + e.getMessage());
    }
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleConstraintViolationException(ConstraintViolationException e) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.getReasonPhrase(), e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(errorResponse);
    }

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<?> handleHttpClientError(ApiException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getStatus().getReasonPhrase(), e.getMessage());
        return ResponseEntity.status(e.getStatus())
                .body(errorResponse);
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<String> handleEmailAlreadyExistsException(AlreadyExistsException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<BaseApi<String>> handleGeneralException(Exception ex) {
        BaseApi<String> response = BaseApi.<String>builder()
                .message("An error occurred: " + ex.getMessage())
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .status(false)
                .timeStamp(LocalDateTime.now())
                .data(null)
                .build();

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    public ResponseEntity<BaseApi<String>> handleIllegalArgumentException(IllegalArgumentException ex) {
        BaseApi<String> response = BaseApi.<String>builder()
                .message("Invalid input: " + ex.getMessage())
                .code(HttpStatus.BAD_REQUEST.value())
                .status(false)
                .timeStamp(LocalDateTime.now())
                .data(null)
                .build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<?> handleDuplicateCourseCodeException(BusinessException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new BaseApi<>(ex.getMessage(), HttpStatus.BAD_REQUEST.value(), false, null, null));
    }

}
