import { RouterserveService } from './../routerserve.service';
import { Component } from '@angular/core';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent {

  constructor(private routerserverobj : RouterserveService) {}

  openhome() {
    this.routerserverobj.openHome();
  }

  openlogin() {
    this.routerserverobj.openLogin();
  }

  opendash() {
    this.routerserverobj.openDashboard();
  }
}
