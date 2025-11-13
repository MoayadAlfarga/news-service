package com.appswaves.exceptions;

import com.appswaves.dto.errors.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

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
}
