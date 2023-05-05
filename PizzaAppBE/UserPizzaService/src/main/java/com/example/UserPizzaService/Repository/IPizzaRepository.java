package com.example.UserPizzaService.Repository;

import com.example.UserPizzaService.Domain.Pizza;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPizzaRepository extends MongoRepository<Pizza, Integer> {
}
