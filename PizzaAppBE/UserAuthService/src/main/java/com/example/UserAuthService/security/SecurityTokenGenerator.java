package com.example.UserAuthService.security;

import com.example.UserAuthService.Domain.User;

import java.util.Map;

public interface SecurityTokenGenerator {

    Map<String,String> generateJwt(User user);

}
