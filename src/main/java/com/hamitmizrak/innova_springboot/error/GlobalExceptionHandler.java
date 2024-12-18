package com.hamitmizrak.innova_springboot.error;


import com.hamitmizrak.innova_springboot.exception._400_BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // NullPointerException
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String> handleNullPointerException(NullPointerException nullPointerException) {
        return new ResponseEntity<>(nullPointerException.getMessage(), HttpStatus.BAD_REQUEST);
    }

    // badRequestException
    @ExceptionHandler(_400_BadRequestException.class)
    public ResponseEntity<String> handleNullPointerException(_400_BadRequestException badRequestException) {
        return new ResponseEntity<>(badRequestException.getMessage(), HttpStatus.BAD_REQUEST);
    }

    // Genel Exception: Özel yazmadığımız(_400_BadRequestException) bütün Exceptionları yakar
    /*
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleCommonrException(Exception exception) {
        return new ResponseEntity<>("Beklenmeyen bir hata: "+exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    */
} //end GlobalExceptionHandler
