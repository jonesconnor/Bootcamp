import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import {Location} from '@angular/common'
@Injectable({
  providedIn: 'root'
})
export class RouteserveService {

  constructor(private routeobj : Router,private locaobj : Location) { }

openHome()
{
  this.routeobj.navigate(['home'])
}
openLogin()
{
  this.routeobj.navigate(['login'])
}

opeDashboard()
{
  this.routeobj.navigate(['dashboard'])
}


openListview()
{
 
this.routeobj.navigate(['dashboard',
{
  outlets : { listOutlet : ['listview']}
}
])


}

openGridview()
{
  this.routeobj.navigate(['dashboard/gridview'])
}
openEmployee(role:string)
{
  this.routeobj.navigate(['dashboard/employee/'+role])
}
goBack()
{
  this.locaobj.back();
}

goForward()
{
  this.locaobj.forward();
}

}
