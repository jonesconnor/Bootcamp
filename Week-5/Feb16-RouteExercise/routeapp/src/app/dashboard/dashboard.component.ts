import { MyrouteService } from './../myroute.service';
import { Component } from '@angular/core';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent {

  constructor(private myrouteobj: MyrouteService) {}

  showUserView() {
    this.myrouteobj.openUserView();
  }

  showClientView() {
    this.myrouteobj.openClientView();
  }

}
