import { Component } from '@angular/core';
import { Pizza } from '../Modules/pizza';
import { UserPizzaService } from '../Services/user-pizza.service';
import { LoginService } from '../Services/login.service';
import { Order } from '../Modules/order';
import { RouterService } from '../Services/router.service';

@Component({
  selector: 'app-pizza-cart',
  templateUrl: './pizza-cart.component.html',
  styleUrls: ['./pizza-cart.component.css']
})
export class PizzaCartComponent {

  cartList:Pizza[]=[];
  totalPrice=0;
  order:Order={
    orderId: 0,
    orderPizzaList: [],
    orderTotalPrice: 0,
    orderDate: new Date()
  };

  constructor(private userService:UserPizzaService, private loginService:LoginService, private routerService:RouterService) {}

  ngOnInit(): void {
    this.userService.fetchCartList(this.loginService.logged).subscribe({
      next:(data)=>{
        this.cartList=data;
        this.findTotalPrice();
        console.log(data);
      },
      error:(error)=>{
        console.log(error);
        if(!this.loginService.isAuthentication()){
          alert("Please login First than Check Cart List");
          this.routerService.navigateToLoginView();
        }
        else{
          alert("Error");
        }
      }
    });
  }

  findTotalPrice(){
    this.totalPrice = this.cartList.reduce((sum,pizza) => sum+(pizza.pizzaPrice*pizza.pizzaQuantity),0);
  }

  updateItem(cartItem:Pizza, qty:number){
    if(qty<=1){
      cartItem.pizzaQuantity=1;
    }
    else{
      cartItem.pizzaQuantity=qty;
    }
    this.userService.updateInCart(cartItem)
    .subscribe({
      next:(data)=>{
        console.log(data);
        this.findTotalPrice();
      },
      error:(error)=>{
        console.log(error);
        alert(error+"!an error has been occured on the server");
      }
    });
  }

  saveOrder(){
    this.order.orderDate=new Date();
    this.order.orderPizzaList=this.cartList;
    this.order.orderTotalPrice=this.totalPrice;
    this.userService.saveOrder(this.order)
    .subscribe(data => {
      console.log(data);
      this.userService.deleteCartItems().subscribe(data => console.log(data));
      this.routerService.navigateToOrderHistoryView();
    });
  }

  deleteItem(id:number){
    this.cartList = this.cartList.filter( pizza1 => pizza1.pizzaId != id);
    this.findTotalPrice();
    this.userService.deleteItemFromCart(id).subscribe(data => console.log(data));
    
  }

}
