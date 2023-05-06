import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { UserLoginComponent } from './user-login/user-login.component';
import { UserRegisterComponent } from './user-register/user-register.component';
import { OrderHistoryComponent } from './order-history/order-history.component';
import { PizzaCartComponent } from './pizza-cart/pizza-cart.component';
import { NotFoundComponent } from './not-found/not-found.component';
import { UserLogoutComponent } from './user-logout/user-logout.component';
import { AuthGuardGuard } from './Services/auth-guard.guard';

const routes: Routes = [
  {
    path: 'home', component: HomeComponent
  },
  { path: "", redirectTo: 'home', pathMatch: 'full' },
  {
    path: 'user-login', component: UserLoginComponent
  },
  {
    path: 'user-logout', component: UserLogoutComponent
  },
  {
    path: 'user-register', component: UserRegisterComponent
  },
  {
    path: 'pizza-cart', component: PizzaCartComponent, canActivate:[AuthGuardGuard]
  },
  {
    path: 'order-history', component: OrderHistoryComponent, canActivate:[AuthGuardGuard]
  },
  { path: "**", component: NotFoundComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
