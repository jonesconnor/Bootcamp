import { Component } from '@angular/core';
import { RouteserveService } from '../services/routeserve.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent {

  constructor(private routeobj:RouteserveService){}
  signout()
  {
this.routeobj.openLogin();
sessionStorage.clear()
  }
}
