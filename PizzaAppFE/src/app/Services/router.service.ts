import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class RouterService {

  constructor(private router: Router) { }

  navigateToHomeView() {
    this.router.navigate(["/home"]);
  }

  navigateToRegisterView() {
    this.router.navigate(["/user-register"]);
  }

  navigateToLoginView(){
    this.router.navigate(["/user-login"]); 
  }

  navigateToPizzaCartView(){
    this.router.navigate(["/pizza-cart"]); 
  }

  navigateToOrderHistoryView(){
    this.router.navigate(["/order-history"]); 
  }
}
