import { Component } from '@angular/core';

import { RouterService } from 'src/app/healthroute/Service/router.service';

@Component({
  selector: 'app-admindashboard',
  templateUrl: './admindashboard.component.html',
  styleUrls: ['./admindashboard.component.css'],
})
export class AdmindashboardComponent {
  constructor(private router: RouterService) {}

  SPRegister() {
    this.router.openSPRegister();
  }

  goToPaymentsDashboard() {
    this.router.openPaymentDashboard();
  }

  goToSearchSP() {
    this.router.openSearchProviders();
  }
}
