package com.example.UserAuthService.security;

import com.example.UserAuthService.Domain.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;
import java.lang.String;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JWTSecurityTokenGeneratorImpl implements SecurityTokenGenerator {

    @Override
    public Map<String, String> generateJwt(User user) {

        String jwtToken = Jwts.builder()
                .setSubject(user.getUserEmail())
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256,"mySecret")
                //mysecret is the key that has to be shared everytime you do encrypt and decrypt process
                .compact();

        Map<String, String> result = new HashMap<>();
        result.put("token",jwtToken);
        result.put("message", "Login Success, Token Generated");

        return result;
    }

}
