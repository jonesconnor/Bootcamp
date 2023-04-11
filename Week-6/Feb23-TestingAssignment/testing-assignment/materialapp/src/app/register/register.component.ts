import { EmpserveService } from './../empserve.service';
import { Component } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { RouteserveService } from '../routeserve.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {

constructor(private snackobj: MatSnackBar,public routeserve:RouteserveService)
{

}

  register()
  {
    this.snackobj.open("Registered","User",
    {
      duration:2000
    }

    )
  }

  login()
  {
    this.routeserve.openLogin()
  }
}
