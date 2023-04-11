import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class RouterService {

  constructor(private routeobj : Router) { }

  openLogin() {
    this.routeobj.navigate(['login']);
  }

  openRegistration() {
    this.routeobj.navigate(['register']);
  }

  openCustomerDashboard() {
    this.routeobj.navigate(['customerdashboard']);
  }

  openAdminDashboard() {
    this.routeobj.navigate(['admindashboard']);
  }

}
