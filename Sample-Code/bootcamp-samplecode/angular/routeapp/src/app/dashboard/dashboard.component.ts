import { Component } from '@angular/core';
import { RouteserveService } from '../routeserve.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent {
uname:any
  constructor(private serobj: RouteserveService)
  {

  let status=  sessionStorage.getItem("isLoggedin");
 this.uname=localStorage.getItem("username");
  }
  gotoBack()
  {
    this.serobj.goBack();
  }
  gotoForward()
  {
    this.serobj.goForward();
  }
  callEmp(r:string)
  {
    sessionStorage.setItem("verified","yes")
    this.serobj.openEmployee(r);
  }
  callListview()
  {
    sessionStorage.setItem("verified","yes")
    this.serobj.openListview();
  }

  callGrid()
  {
    sessionStorage.setItem("verified","yes")
    this.serobj.openGridview();
  }
}
