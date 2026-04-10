package com.example.Redis_Sb.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalException {

    //handle specific exception
    @ExceptionHandler(UserNotFoundException.class)
    //gives you structured JSON response
    public ResponseEntity<ErrorResponse> UserNotFoundException(UserNotFoundException ex){

        //before adding error response class ==> gives you the plain text(string) in output
        // public ResponseEntity<String> UserNotFoundException(UserNotFoundException ex) {
        //return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);


        //after adding error response class ==> gives you the structured JSON type response
        ErrorResponse error=new ErrorResponse(
                ex.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);


    }


    //handle all exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> Exception(Exception e){
        return new ResponseEntity<>("Something went wrong",HttpStatus.INTERNAL_SERVER_ERROR);
    }
}