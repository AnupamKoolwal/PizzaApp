package com.example.UserPizzaService.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FOUND, reason = "User Already Exists Exception")
public class UserAlreadyExistsException extends Exception{

}
