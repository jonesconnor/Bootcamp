import { Component } from '@angular/core';
import { AuthenticateService } from '../services/authenticate.service';
import { RouteserveService } from '../services/routeserve.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
constructor(private routeobj : RouteserveService,private authserve : AuthenticateService)
{

}

signin(user:string,password:string)
{
 //this.routeobj.openDashboard()

  let data={
    "username":user,
    "password":password
  }


 this.authserve.generateToken(data).subscribe(
  (res)=>
  {
  
    sessionStorage.setItem("mytoken",res["token"])
    console.log(res["token"])
    this.routeobj.openDashboard()
  }
 )  

}
}
