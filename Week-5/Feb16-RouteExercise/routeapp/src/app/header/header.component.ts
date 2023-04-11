import { Component } from '@angular/core';
import { MyrouteService } from '../myroute.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent {

  constructor(private myrouteobj: MyrouteService) {}

  showHome() {
    this.myrouteobj.openHome();
  }

  showLogin() {
    this.myrouteobj.openLogin();
  }

}
