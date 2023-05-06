import { Component, Input, OnInit } from '@angular/core';
import { PizzaService } from '../Services/pizza.service';
import { Pizza } from '../Modules/pizza';
import { UserPizzaService } from '../Services/user-pizza.service';
import { LoginService } from '../Services/login.service';
import { RouterService } from '../Services/router.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})

export class HomeComponent implements OnInit {

  allPizza: any = [];
  checkStatus: boolean = false;

  constructor(private pizzaService: PizzaService, private userService: UserPizzaService, private loginService: LoginService, private routerService: RouterService) { }

  ngOnInit() {
    this.checkStatus = this.loginService.isAuthentication();
    console.log(this.checkStatus);
    console.log("ab "+this.loginService.isAuthentication());
    this.pizzaService.fetchAllPizzas().subscribe({
      next: (data) => {
        this.allPizza = data;
        console.log(data);
      },
      error: (error) => {
        console.log(error);
        alert(error + "!an error has been occured on the server");
      }
    });
  }
  
  addToCart(pizza1: Pizza) {
    pizza1.pizzaQuantity = 1;
    if (!this.loginService.isAuthentication()) {
      alert("Please login First than Add to cart");
      this.routerService.navigateToLoginView();
    }
    else {
      this.userService.saveItemToCart(pizza1)
        .subscribe({
          next: (data) => {
            console.log(data);
            alert("Saved Successfully!");
          },
          error: (error) => {
            console.log(error);
            alert("This Pizza Already Added");
            this.routerService.navigateToPizzaCartView();
        }
      });
    }
    }
}
  