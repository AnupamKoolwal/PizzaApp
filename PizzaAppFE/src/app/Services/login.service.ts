import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Login } from '../Modules/login';
import { User } from '../Modules/user';
import { RouterService } from './router.service';

@Injectable({
  providedIn: 'root'
})

export class LoginService {

  constructor(private httpClient:HttpClient, private routerService:RouterService) { }

  isLoggedIn:boolean=false;
  logged:Login={
    message: '',
    token: ''
  };

  loginUser(login:User){
    this.httpClient.post('http://localhost:9002/api/v1/loginUser',login).subscribe({
      next:(data:any)=>{
        console.log(data)
        this.logged=data;
        sessionStorage.setItem("token",data.token);
        localStorage.setItem('token',data.token);
        console.log("Data save in user")
        console.log(this.logged);
        console.log("Done")
        this.isLoggedIn=true;
        sessionStorage.setItem("status",<string><unknown>this.isLoggedIn);
        console.log(this.isLoggedIn)
        alert("Login Successfully");
        this.routerService.navigateToHomeView();
      },
      error:(error)=>{
        alert("Please Login Again");
      }
      
    });
  }

  logoutUser() {
    sessionStorage.removeItem('token');
    localStorage.removeItem('token');
    this.isLoggedIn = false;
    sessionStorage.removeItem('status');
    this.routerService.navigateToLoginView();
  }

  isAuthentication(){
    return <boolean><unknown>sessionStorage.getItem('status');
  }
}
