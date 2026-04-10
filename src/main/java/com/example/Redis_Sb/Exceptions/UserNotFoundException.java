package com.example.Redis_Sb.Exceptions;

import com.example.Redis_Sb.Model.User;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String message){
        super(message);
    }
}
