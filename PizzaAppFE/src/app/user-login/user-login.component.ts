import { Component } from '@angular/core';
import { FormGroup, FormBuilder, FormControl, Validators, AbstractControl } from '@angular/forms';
import { User } from '../Modules/user';
import { LoginService } from '../Services/login.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-user-login',
  templateUrl: './user-login.component.html',
  styleUrls: ['./user-login.component.css']
})
export class UserLoginComponent {

  token = "";
  hide = true;
  user:User={
    userName: '',
    userPassword: '',
    userPhoneNo: 0
  };

  loginForm: FormGroup;
  constructor(private formBuilder:FormBuilder,private loginService:LoginService)
  {
    this.loginForm=this.formBuilder.group({
      userEmail:new FormControl('',[Validators.required, Validators.pattern(/^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/)]),
      userPassword:new FormControl('',[Validators.required,Validators.minLength(8)])
    });

  }
  
  get userEmail(){
    return this.loginForm.get('userEmail');
  }
  get userPassword(){
    return this.loginForm.get('userPassword');
  }

  onSubmit() {
    console.log(this.loginForm.value);
    let login:User={
      userEmail: this.userEmail?.value,
      userPassword: this.loginForm.get("userPassword")?.value,
      userName: '',
      userPhoneNo: 0
    }
    this.loginService.loginUser(login);
 }

}
