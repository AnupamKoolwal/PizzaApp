package com.example.UserPizzaService.Service;

import com.example.UserPizzaService.Domain.Order;
import com.example.UserPizzaService.Domain.Pizza;
import com.example.UserPizzaService.Domain.User;
import com.example.UserPizzaService.Exceptions.PizzaAlreadyExistsException;
import com.example.UserPizzaService.Exceptions.PizzaNotFoundException;
import com.example.UserPizzaService.Exceptions.UserAlreadyExistsException;
import com.example.UserPizzaService.Exceptions.UserNotFoundException;
import com.example.UserPizzaService.Proxy.UserProxy;
import com.example.UserPizzaService.Repository.IPizzaRepository;
import com.example.UserPizzaService.Repository.IUserPizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@Service
public class UserPizzaServiceImpl implements IUserPizzaService{

    private IUserPizzaRepository userPizzaRepository;
    private IPizzaRepository pizzaRepository;
    private UserProxy userProxy;

    @Autowired
    public UserPizzaServiceImpl(IUserPizzaRepository userPizzaRepository, IPizzaRepository pizzaRepository, UserProxy userProxy) {
        this.userPizzaRepository = userPizzaRepository;
        this.pizzaRepository = pizzaRepository;
        this.userProxy = userProxy;
    }

    @Override
    public User registerUser(User user) throws UserAlreadyExistsException {
        if(userPizzaRepository.findById(user.getUserEmail()).isPresent()){
            throw new UserAlreadyExistsException();
        }
        User savedUser = userPizzaRepository.save(user);
        if (!(savedUser.getUserEmail().isEmpty())){
            ResponseEntity responseEntity = userProxy.registerUser(savedUser);
            System.out.println(responseEntity.getBody());
        }
        return savedUser;
    }

    @Override
    public Pizza savePizza(Pizza pizza) throws PizzaAlreadyExistsException {
        if (pizzaRepository.findById(pizza.getPizzaId()).isPresent()){
            throw new PizzaAlreadyExistsException();
        }
        return pizzaRepository.save(pizza);
    }

    @Override
    public List<Pizza> getAllPizza() throws PizzaNotFoundException{
        if (pizzaRepository.findAll().isEmpty()){
            throw new PizzaNotFoundException();
        }
        return pizzaRepository.findAll();
    }

    @Override
    public User saveItemToCart(Pizza pizza, String userEmail) throws UserNotFoundException, PizzaAlreadyExistsException {
        if(userPizzaRepository.findById(userEmail).isEmpty())
        {
            throw new UserNotFoundException();
        }
        User user = userPizzaRepository.findByUserEmail(userEmail);

        if(user.getUserPizzaCartList() == null)
        {
            user.setUserPizzaCartList(Arrays.asList(pizza));
        }
        else {
            List<Pizza> pizzas = user.getUserPizzaCartList();

            for (Pizza pizza1: pizzas){
                if (pizza1.getPizzaId() == pizza.getPizzaId()){
                    throw new PizzaAlreadyExistsException();
                }
            }

            pizzas.add(pizza);
            user.setUserPizzaCartList(pizzas);
        }
        return userPizzaRepository.save(user);
    }

    @Override
    public List<Pizza> getCartItems(String userEmail) throws UserNotFoundException {
        if(userPizzaRepository.findById(userEmail).isEmpty())
        {
            throw new UserNotFoundException();
        }
        return userPizzaRepository.findById(userEmail).get().getUserPizzaCartList();
    }

    @Override
    public User deleteItemFromCart(String email, int pizzaId) throws UserNotFoundException {
        if(userPizzaRepository.findById(email).isEmpty())
        {
            throw new UserNotFoundException();
        }
        User user = userPizzaRepository.findById(email).get();
        List<Pizza> pizzas = user.getUserPizzaCartList();
        Iterator<Pizza> iterator = pizzas.iterator();
        while (iterator.hasNext()) {
            Pizza pizza = iterator.next();
            if (pizza.getPizzaId() == pizzaId) {
                iterator.remove();
                break;
            }
        }
        user.setUserPizzaCartList(pizzas);
        return userPizzaRepository.save(user);
    }

    @Override
    public User updateCartItem(String userEmail, Pizza pizza, int pizzaId) throws UserNotFoundException, PizzaNotFoundException {
        if(userPizzaRepository.findById(userEmail).isEmpty())
        {
            throw new UserNotFoundException();
        }
        User existingUser = userPizzaRepository.findByUserEmail(userEmail);
        List<Pizza> pizzas = existingUser.getUserPizzaCartList();
        boolean pizzaFound = false;

        for (Pizza pizza1 : pizzas) {
            if (pizza1.getPizzaId() == pizzaId) {
                pizza1.setPizzaName(pizza.getPizzaName());
                pizza1.setPizzaPrice(pizza.getPizzaPrice());
                pizza1.setPizzaQuantity(pizza.getPizzaQuantity());
                pizza1.setPizzaSize(pizza.getPizzaSize());
                pizzaFound = true;
                break;
            }
        }
        if (!pizzaFound) {
            throw new PizzaNotFoundException();
        }

        existingUser.setUserPizzaCartList(pizzas);
        return userPizzaRepository.save(existingUser);
    }

    @Override
    public User saveOrder(Order order, String email) throws UserNotFoundException {
        if(userPizzaRepository.findById(email).isEmpty())
        {
            throw new UserNotFoundException();
        }
        User user = userPizzaRepository.findById(email).get();
        if(user.getUserOrderList() == null)
        {
            order.setOrderId(1);
            user.setUserOrderList(Arrays.asList(order));
        }
        else {
            int lastIndex = user.getUserOrderList().size()-1;
            int lastOrderId = user.getUserOrderList().get(lastIndex).getOrderId();
            order.setOrderId(lastOrderId+1);
            List<Order> orders = user.getUserOrderList();
            orders.add(order);
            user.setUserOrderList(orders);
        }
        return userPizzaRepository.save(user);
    }

    @Override
    public List<Order> getAllOrders(String email) throws UserNotFoundException {
        if(userPizzaRepository.findById(email).isEmpty())
        {
            throw new UserNotFoundException();
        }
        return userPizzaRepository.findByUserEmail(email).getUserOrderList();
    }

    @Override
    public User deleteAllItemFromCart(String email) throws UserNotFoundException {
        if(userPizzaRepository.findById(email).isEmpty())
        {
            throw new UserNotFoundException();
        }
        User user = userPizzaRepository.findById(email).get();
        user.setUserPizzaCartList(null);
        return userPizzaRepository.save(user);
    }

}
