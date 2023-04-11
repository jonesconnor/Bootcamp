import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class MyrouteService {

  constructor(private routerserviceobj: Router) { }

  openHome() {
    this.routerserviceobj.navigate(['home']);
  }

  openLogin() {
    this.routerserviceobj.navigate(['login']);
  }

  openDashboard() {
    this.routerserviceobj.navigate(['dashboard']);
  }

  openClientView() {
    this.routerserviceobj.navigate(['dashboard/clientview']);
  }

  openUserView() {
    this.routerserviceobj.navigate(['dashboard/userview']);
  }
}
