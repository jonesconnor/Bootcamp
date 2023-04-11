import { Component } from '@angular/core';
import { RouteserveService } from '../routeserve.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  msg:string=""
  flag:boolean=false
constructor(private serviceobj:RouteserveService) {
  
}

  callDashboard(uname:string, password:string)
{
    
  if (uname==="admin" && password==="pass@123")
  {
    sessionStorage.setItem("isLoggedin","true");
    localStorage.setItem("username",uname);
    this.flag=true
  this.serviceobj.opeDashboard();


  }
  else
  {
  this.msg="Invalid Credentials";
    this.flag=false
  }
}

}
