package com.example.UserPizzaService.Repository;

import com.example.UserPizzaService.Domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserPizzaRepository extends MongoRepository<User, String> {

    User findByUserEmail(String userEmail);

}
