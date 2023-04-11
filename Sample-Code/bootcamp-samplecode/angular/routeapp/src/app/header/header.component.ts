import { Component } from '@angular/core';
import { RouteserveService } from '../routeserve.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent {
constructor(private rotuobj: RouteserveService)

{ }
  openhome()
  {
     this.rotuobj.openHome();
  }
  openlogin()
  {
    this.rotuobj.openLogin();
  }

}

