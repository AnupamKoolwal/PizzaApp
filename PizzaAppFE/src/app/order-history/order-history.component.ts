import { Component, ViewChild } from '@angular/core';
import { MatAccordion } from '@angular/material/expansion';
import { Order } from '../Modules/order';
import { UserPizzaService } from '../Services/user-pizza.service';
import { LoginService } from '../Services/login.service';
import { RouterService } from '../Services/router.service';

@Component({
  selector: 'app-order-history',
  templateUrl: './order-history.component.html',
  styleUrls: ['./order-history.component.css']
})
export class OrderHistoryComponent {

  @ViewChild(MatAccordion)
  accordion!: MatAccordion;

  orders:Order[]=[];

  constructor(private userService:UserPizzaService, private loginService: LoginService, private routerService:RouterService) {}

  ngOnInit(): void {
    this.userService.fetchAllOrders().subscribe({
      next:(data)=>{
        this.orders=data;
        console.log(data);
      },
      error:(error)=>{
        console.log(error);
        if(!this.loginService.isAuthentication()){
          alert("Please login First than Check Order History");
          this.routerService.navigateToLoginView();
        }
        else{
          alert("Error");
        }
      }
    });
}

}
