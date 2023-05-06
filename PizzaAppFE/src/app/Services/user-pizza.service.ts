import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Login } from '../Modules/login';
import { Order } from '../Modules/order';
import { Pizza } from '../Modules/pizza';
import { User } from '../Modules/user';

@Injectable({
  providedIn: 'root'
})

export class UserPizzaService {

  constructor(private httpClient:HttpClient) { }

  saveUser(user:User){
    return this.httpClient.post<User>("http://localhost:9002/api/v2/register",user);
  }

  saveItemToCart(pizza:Pizza){
    const requestHeader = new HttpHeaders().set('Authorization','Bearer '+window.sessionStorage.getItem('token'));
    return this.httpClient.post("http://localhost:9002/api/v2/user/saveCart",pizza,{'headers':requestHeader});
  }
  
  fetchCartList(login:Login){
    const requestHeader = new HttpHeaders().set('Authorization','Bearer '+window.sessionStorage.getItem('token'));
    return this.httpClient.get<Array<Pizza>>("http://localhost:9002/api/v2/user/getCartItems",{'headers':requestHeader});
  }

  updateInCart(pizza:Pizza){
    const requestHeader = new HttpHeaders().set('Authorization','Bearer '+window.sessionStorage.getItem('token'));
    return this.httpClient.put("http://localhost:9002/api/v2/user/updateCartItem/"+pizza.pizzaId,pizza,{'headers':requestHeader});
  }

  saveOrder(order:Order){
    const requestHeader = new HttpHeaders().set('Authorization','Bearer '+window.sessionStorage.getItem('token'));
    return this.httpClient.post("http://localhost:9002/api/v2/user/saveOrder",order,{'headers':requestHeader});
  }

  fetchAllOrders(){
    const requestHeader = new HttpHeaders().set('Authorization','Bearer '+window.sessionStorage.getItem('token'));
    return this.httpClient.get<Array<Order>>("http://localhost:9002/api/v2/user/getAllOrders",{'headers':requestHeader});
  }

  deleteCartItems(){
    const requestHeader = new HttpHeaders().set('Authorization','Bearer '+window.sessionStorage.getItem('token'));
    return this.httpClient.delete("http://localhost:9002/api/v2/user/deleteAllItemFromCart",{'headers':requestHeader})
  }

  deleteItemFromCart(id:number){
    const requestHeader = new HttpHeaders().set('Authorization','Bearer '+window.sessionStorage.getItem('token'));
    return this.httpClient.delete("http://localhost:9002/api/v2/user/cart/"+id,{'headers':requestHeader})
  }

}
