package com.example.UserPizzaService.Controller;

import com.example.UserPizzaService.Domain.Order;
import com.example.UserPizzaService.Domain.Pizza;
import com.example.UserPizzaService.Domain.User;
import com.example.UserPizzaService.Exceptions.PizzaAlreadyExistsException;
import com.example.UserPizzaService.Exceptions.PizzaNotFoundException;
import com.example.UserPizzaService.Exceptions.UserAlreadyExistsException;
import com.example.UserPizzaService.Exceptions.UserNotFoundException;
import com.example.UserPizzaService.Service.UserPizzaServiceImpl;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v2")
public class UserPizzaController {

    private ResponseEntity<?> responseEntity;

    private UserPizzaServiceImpl userPizzaService;

    @Autowired
    public UserPizzaController(UserPizzaServiceImpl userPizzaService) {
        this.userPizzaService = userPizzaService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) throws UserAlreadyExistsException {
        try {
            responseEntity = new ResponseEntity<>(this.userPizzaService.registerUser(user), HttpStatus.CREATED);
        }
        catch(UserAlreadyExistsException e)
        {
            throw new UserAlreadyExistsException();
        }
        return responseEntity;
    }

    @PostMapping("/savePizza")
    public ResponseEntity<?> savePizza(@RequestBody Pizza pizza) throws PizzaAlreadyExistsException {
        try {
            responseEntity =  new ResponseEntity<>(userPizzaService.savePizza(pizza), HttpStatus.CREATED);
        }
        catch(PizzaAlreadyExistsException e)
        {
            throw new PizzaAlreadyExistsException();
        }
        return responseEntity;
    }

    @GetMapping("/getAllPizza")
    public ResponseEntity<?> getAllPizza() throws PizzaNotFoundException {
        try{
            responseEntity = new ResponseEntity<>(userPizzaService.getAllPizza(),HttpStatus.OK);
        } catch (PizzaNotFoundException e) {
            throw new PizzaNotFoundException();
        }
        return responseEntity;
    }

    @PostMapping("/user/saveCart")
    public ResponseEntity<?> saveItemToCart(@RequestBody Pizza pizza, HttpServletRequest request) throws UserNotFoundException, PizzaAlreadyExistsException {
        try {
            Claims claims = (Claims) request.getAttribute("claims");
            String userEmail = claims.getSubject();
            System.out.println("email = "+userEmail);
            responseEntity = new ResponseEntity<>(userPizzaService.saveItemToCart(pizza, userEmail), HttpStatus.CREATED);
        }
        catch (UserNotFoundException e)
        {
            throw new UserNotFoundException();
        } catch (PizzaAlreadyExistsException e) {
            throw new PizzaAlreadyExistsException();
        }
        return responseEntity;
    }

    @GetMapping("/user/getCartItems")
    public ResponseEntity<?> getCartItems(HttpServletRequest httpServletRequest) throws UserNotFoundException{
        try{
            Claims claims = (Claims) httpServletRequest.getAttribute("claims");
            String userEmail = claims.getSubject();
            System.out.println("email = "+userEmail);
            responseEntity = new ResponseEntity<>(userPizzaService.getCartItems(userEmail), HttpStatus.OK);
        }catch(UserNotFoundException e)
        {
            throw new UserNotFoundException();
        }
        return responseEntity;
    }

    @DeleteMapping("/user/cart/{pizzaId}")
    public ResponseEntity<?> deleteItemFromCart(@PathVariable int pizzaId, HttpServletRequest httpServletRequest) throws UserNotFoundException{
        Claims claims = (Claims) httpServletRequest.getAttribute("claims");
        String userEmail = claims.getSubject();
        System.out.println("email = "+userEmail);
        try {
            responseEntity = new ResponseEntity<>(userPizzaService.deleteItemFromCart(userEmail, pizzaId), HttpStatus.OK);
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException();
        }
        catch (Exception e) {
            return new ResponseEntity<>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @PutMapping("/user/updateCartItem/{pizzaId}")
    public ResponseEntity<?> updateCartItem(HttpServletRequest httpServletRequest, @PathVariable int pizzaId, @RequestBody Pizza pizza) throws UserNotFoundException, PizzaNotFoundException{
        Claims claims = (Claims) httpServletRequest.getAttribute("claims");
        String userEmail = claims.getSubject();
        System.out.println("email = "+userEmail);
        try{
            return new ResponseEntity<>(userPizzaService.updateCartItem(userEmail, pizza, pizzaId), HttpStatus.OK);
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException();
        } catch (PizzaNotFoundException e) {
            throw new PizzaNotFoundException();
        } catch (Exception e) {
            return new ResponseEntity<>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/user/saveOrder")
    public ResponseEntity<?> saveOrder(@RequestBody Order order, HttpServletRequest httpServletRequest) throws UserNotFoundException{
        try {
            Claims claims = (Claims) httpServletRequest.getAttribute("claims");
            String userEmail = claims.getSubject();
            System.out.println("email = "+userEmail);
            responseEntity = new ResponseEntity<>(userPizzaService.saveOrder(order, userEmail), HttpStatus.CREATED);
        }
        catch (UserNotFoundException e)
        {
            throw new UserNotFoundException();
        }catch (Exception e) {
            return new ResponseEntity<>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @GetMapping("/user/getAllOrders")
    public ResponseEntity<?> getAllOrders(HttpServletRequest httpServletRequest) throws UserNotFoundException{
        try{
            Claims claims = (Claims) httpServletRequest.getAttribute("claims");
            String userEmail = claims.getSubject();
            System.out.println("email = "+userEmail);
            responseEntity = new ResponseEntity<>(userPizzaService.getAllOrders(userEmail), HttpStatus.OK);
        }catch(UserNotFoundException e)
        {
            throw new UserNotFoundException();
        }catch (Exception e) {
            return new ResponseEntity<>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @DeleteMapping("/user/deleteAllItemFromCart")
    public ResponseEntity<?> deleteAllItemFromCart(HttpServletRequest httpServletRequest) throws UserNotFoundException{
        Claims claims = (Claims) httpServletRequest.getAttribute("claims");
        String userEmail = claims.getSubject();
        System.out.println("email = "+userEmail);
        try {
            responseEntity = new ResponseEntity<>(userPizzaService.deleteAllItemFromCart(userEmail), HttpStatus.OK);
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException();
        }catch (Exception e) {
            return new ResponseEntity<>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
}
