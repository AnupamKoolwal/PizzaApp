import { Component } from '@angular/core';
import { FormGroup, FormBuilder, FormControl, Validators, AbstractControl } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { User } from '../Modules/user';
import { UserPizzaService } from '../Services/user-pizza.service';

@Component({
  selector: 'app-user-register',
  templateUrl: './user-register.component.html',
  styleUrls: ['./user-register.component.css']
})
export class UserRegisterComponent {

  hide = true;
  
  user: User = {
    userName: '',
    userPassword: '',
    userPhoneNo: 0
  };

  register: FormGroup;

  constructor(private formBuilder: FormBuilder, private userService: UserPizzaService, private _snackBar: MatSnackBar) {
    this.register = this.formBuilder.group({
      userName: new FormControl('', [Validators.required]),
      userEmail: new FormControl('', [Validators.required, Validators.pattern(/^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/)]),
      userPassword: new FormControl('', [Validators.required, Validators.minLength(8)]),
      userPhoneNo: new FormControl('', [Validators.required, Validators.pattern(/^[789]\d{9,9}$/)]),
    });

  }

  get userName() {
    return this.register.get('userName');
  }
  get userEmail() {
    return this.register.get('userEmail');
  }
  get userPassword() {
    return this.register.get('userPassword');
  }
  get userPhoneNo() {
    return this.register.get('userPhoneNo');
  }

  onSubmit() {
    this.user = this.register.value;
    console.log(this.user);
    console.log(this.register.value);

    this.userService.saveUser(this.user).subscribe({
      next: (data) => {
        alert("User added Successfully");
        this._snackBar.open('Congrats, you have submitted the form!!', 'success', {
          duration: 5000
        });
      },
      error: (error) => {
        alert("Email address already exist");
        this.register.reset();
      }
    });
  }

}
