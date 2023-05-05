package com.example.UserPizzaService.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "User Not Found Exception")
public class UserNotFoundException extends Exception{
}
