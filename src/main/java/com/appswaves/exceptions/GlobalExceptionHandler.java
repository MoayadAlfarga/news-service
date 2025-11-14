package com.appswaves.exceptions;

import com.appswaves.dto.errors.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private ErrorResponseDto buildErrorResponse(String arabicMessage, String englishMessage) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto();
        errorResponseDto.setArabicMessage(arabicMessage);
        errorResponseDto.setEnglishMessage(englishMessage);
        errorResponseDto.setErrorTime(LocalDateTime.now());
        return errorResponseDto;
    }

    @ExceptionHandler(NewsNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handlingAlreadyExistsException(NewsNotFoundException e) {
        return new ResponseEntity<>(buildErrorResponse(e.getArabicMessage(), e.getEnglishMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDto> handlingAlreadyExistsException(AlreadyExistsException e) {
        return new ResponseEntity<>(buildErrorResponse(e.getArabicMessage(), e.getEnglishMessage()), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handlingAlreadyExistsException(UsernameNotFoundException e) {
        return new ResponseEntity<>(buildErrorResponse(e.getArabicMessage(), e.getEnglishMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handlingAlreadyExistsException(UnexpectedErrorException e) {
        return new ResponseEntity<>(buildErrorResponse(e.getArabicMessage(), e.getEnglishMessage()), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // Sets the HTTP status to 400
    public ResponseEntity<Map<String, String>> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
