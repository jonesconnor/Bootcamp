import { MyrouteService } from './../myroute.service';
import { Component } from '@angular/core';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  constructor(private myrouteobj: MyrouteService) {}

  showDash() {
    this.myrouteobj.openDashboard();
  }
}
