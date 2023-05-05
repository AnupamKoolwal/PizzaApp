package com.example.UserPizzaService.Service;

import com.example.UserPizzaService.Domain.Order;
import com.example.UserPizzaService.Domain.Pizza;
import com.example.UserPizzaService.Domain.User;
import com.example.UserPizzaService.Exceptions.PizzaAlreadyExistsException;
import com.example.UserPizzaService.Exceptions.PizzaNotFoundException;
import com.example.UserPizzaService.Exceptions.UserAlreadyExistsException;
import com.example.UserPizzaService.Exceptions.UserNotFoundException;

import java.util.List;

public interface IUserPizzaService {

    User registerUser(User user) throws UserAlreadyExistsException;

    Pizza savePizza(Pizza pizza) throws PizzaAlreadyExistsException;

    List<Pizza> getAllPizza() throws PizzaNotFoundException;

    User saveItemToCart(Pizza pizza, String email) throws UserNotFoundException, PizzaAlreadyExistsException;

    List<Pizza> getCartItems(String email) throws UserNotFoundException;

    User deleteItemFromCart(String email, int pizzaId) throws UserNotFoundException;

    User updateCartItem(String email, Pizza pizza, int pizzaId) throws UserNotFoundException, PizzaNotFoundException;

    User saveOrder(Order order, String email) throws UserNotFoundException;

    List<Order> getAllOrders(String email) throws UserNotFoundException;

    User deleteAllItemFromCart(String email) throws UserNotFoundException;

}
