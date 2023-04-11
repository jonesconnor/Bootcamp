import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class RouteserveService {

  constructor(private routeobj : Router) { }

  openLogin()
  {
    this.routeobj.navigate(['login'])
  }

  openDashboard()
  {
    this.routeobj.navigate(['dashboard'])
  }
}
