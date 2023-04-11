import { Component, OnInit } from '@angular/core';
import jwtDecode from 'jwt-decode';
import { RouterService } from 'src/app/healthroute/Service/router.service';

@Component({
  selector: 'app-payment-dashboard',
  templateUrl: './payment-dashboard.component.html',
  styleUrls: ['./payment-dashboard.component.css'],
})
export class PaymentDashboardComponent implements OnInit {
  dashboard!: string;

  constructor(private router: RouterService) {}

  ngOnInit(): void {
    let getCorrespondingDashboard = () => {
      let token: any = sessionStorage.getItem('bearerToken');
      if (token == null) {
        this.router.openLanding();
      }

      let decoded: any = jwtDecode(token);
      let role: string = decoded['Role'];

      return role;
    };

    this.dashboard = getCorrespondingDashboard();
  }
}
