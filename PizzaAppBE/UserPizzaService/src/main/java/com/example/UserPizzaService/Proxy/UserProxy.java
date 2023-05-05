package com.example.UserPizzaService.Proxy;

import com.example.UserPizzaService.Domain.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "user-auth-service", url = "http://localhost:8086")
public interface UserProxy {

    @PostMapping("/api/v1/registerUser")
    public ResponseEntity<?> registerUser(@RequestBody User user);

}
