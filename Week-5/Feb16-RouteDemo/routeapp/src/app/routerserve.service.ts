import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class RouterserveService {

  constructor(private routeobj : Router) { }

  openHome() {
    this.routeobj.navigate(['home']);
  }

  openLogin() {
    this.routeobj.navigate(['login']);
  }

  openDashboard() {
    this.routeobj.navigate(['dashboard']);
  }

  openListView() {
    this.routeobj.navigate(['dashboard', {
      outlets: {listOutlet : ['listview']}
    }])
  }

  openGridView() {
    this.routeobj.navigate(['dashboard/gridview']);
  }

  openEmployee(role: String) {
    this.routeobj.navigate([`dashboard/employee/${role}`]);
  }

}
