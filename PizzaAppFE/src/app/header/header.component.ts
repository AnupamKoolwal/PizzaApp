import { Component } from '@angular/core';
import { LoginService } from '../Services/login.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent {

  constructor(private loginService: LoginService, private _snackBar: MatSnackBar){}

  logout(){
    this.loginService.logoutUser();
    this._snackBar.open('Congrats, Log-Out Successfully', 'success', {
      duration: 2000
    });
  }
}
