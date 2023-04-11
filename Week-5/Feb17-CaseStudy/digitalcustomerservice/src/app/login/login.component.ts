import { MatSnackBar } from '@angular/material/snack-bar';
import { RouterService } from './../router.service';
import { Component } from '@angular/core';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  constructor(private routeobj : RouterService, private authserve : AuthService, private snackBar: MatSnackBar) {}

  callRegistration() {
    this.routeobj.openRegistration();
  }

  callCustomerDashboard() {
    this.routeobj.openCustomerDashboard();
  }

  signin(user: string, pwd: string) {
    let data = {
      "username": user,
      "password": pwd
    }

    if (data.username === "") {
      this.routeobj.openCustomerDashboard();
    } else {
      this.authserve.generateToken(data).subscribe(
        (res) => {
          sessionStorage.setItem("mytoken", res["token"])
          console.log(res["token"])
          this.routeobj.openAdminDashboard();
        },
        (err : any) => {
          console.log(err);
          this.snackBar.open('Invalid Credentials', 'Close', {
            duration: 5000
          });
        }
      )
    }
  }

}
