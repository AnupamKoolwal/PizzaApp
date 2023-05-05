package com.example.UserAuthService.Services;

import com.example.UserAuthService.Domain.User;
import com.example.UserAuthService.Exception.InvalidCredentialsException;
import com.example.UserAuthService.Exception.UserAlreadyExistException;
import com.example.UserAuthService.Repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService{

    @Autowired
    private IUserRepository userRepository;

    @Override
    public User saveUser(User user) throws UserAlreadyExistException {
        if(userRepository.findById(user.getUserEmail()).isPresent()){
            throw new UserAlreadyExistException();
        }
        return userRepository.save(user);
    }

    @Override
    public User getByUserEmailAndUserPassword(String userEmail, String userPassword) throws InvalidCredentialsException {
        if(userRepository.findByUserEmailAndUserPassword(userEmail,userPassword) == null){
            throw new InvalidCredentialsException();
        }
        return userRepository.findByUserEmailAndUserPassword(userEmail, userPassword);
    }
}
