import { RouterserveService } from './../routerserve.service';
import { Component } from '@angular/core';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  msg ?: string

  constructor(private serviceobj: RouterserveService) {}

  callDash(uname: string, pass: string) {
    if (uname === "admin" && pass === "123") {
      sessionStorage.setItem("isLoggedIn", "true");
      this.serviceobj.openDashboard();
    } else {
      this.msg = "Invalid Credentials";
    }
  }

}
