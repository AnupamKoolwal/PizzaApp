package com.example.UserAuthService.Controller;

import com.example.UserAuthService.Domain.User;
import com.example.UserAuthService.Exception.InvalidCredentialsException;
import com.example.UserAuthService.Exception.UserAlreadyExistException;
import com.example.UserAuthService.Services.UserServiceImpl;
import com.example.UserAuthService.security.JWTSecurityTokenGeneratorImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    private UserServiceImpl userService;
    private JWTSecurityTokenGeneratorImpl jwtSecurityTokenGenerator;

    @Autowired
    public UserController(UserServiceImpl userService, JWTSecurityTokenGeneratorImpl jwtSecurityTokenGenerator) {
        this.userService = userService;
        this.jwtSecurityTokenGenerator = jwtSecurityTokenGenerator;
    }

    @PostMapping("/registerUser")
    public ResponseEntity<?> registerUser(@RequestBody User user) throws UserAlreadyExistException{
        return new ResponseEntity<>(this.userService.saveUser(user), HttpStatus.CREATED);
    }

    @PostMapping("/loginUser")
    public ResponseEntity<?> loginUser(@RequestBody User user) throws InvalidCredentialsException{
        User createdUser = this.userService.getByUserEmailAndUserPassword(user.getUserEmail(), user.getUserPassword());
        if (createdUser == null){
            throw new InvalidCredentialsException();
        }
        Map<String, String> token = this.jwtSecurityTokenGenerator.generateJwt(user);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }
}
