import { Component } from '@angular/core';
import { RouterService } from '../Services/router.service';

@Component({
  selector: 'app-user-logout',
  templateUrl: './user-logout.component.html',
  styleUrls: ['./user-logout.component.css']
})
export class UserLogoutComponent {

  constructor(private routerService: RouterService) { }

  ngOnInit(): void {
    setTimeout(() => {
      this.routerService.navigateToLoginView();
    }, 2000);
  }

}