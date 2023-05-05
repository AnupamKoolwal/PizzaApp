package com.example.UserAuthService.Services;

import com.example.UserAuthService.Domain.User;
import com.example.UserAuthService.Exception.InvalidCredentialsException;
import com.example.UserAuthService.Exception.UserAlreadyExistException;

public interface IUserService {

    User saveUser(User user) throws UserAlreadyExistException;

    User getByUserEmailAndUserPassword(String userEmail,String userPassword) throws InvalidCredentialsException;

}
