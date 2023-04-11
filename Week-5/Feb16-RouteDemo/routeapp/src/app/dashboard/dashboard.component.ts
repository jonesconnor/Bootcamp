import { RouterserveService } from './../routerserve.service';
import { Component } from '@angular/core';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent {

  constructor(private serveobj: RouterserveService) {}

  callEmp(r: string) {
    this.serveobj.openEmployee(r)
  }

}
