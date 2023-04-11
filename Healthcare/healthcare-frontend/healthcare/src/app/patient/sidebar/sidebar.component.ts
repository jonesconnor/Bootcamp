import { Component } from '@angular/core';
import { RouterService } from 'src/app/healthroute/Service/router.service';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css'],
})
export class SidebarComponent {
  constructor(private router: RouterService) {}

  onMedicalHistory() {
    this.router.openMedicalHistory();
  }

  onPaymentHistory() {
    this.router.openPaymentDashboard();
  }
  onPrescription() {
    this.router.openPrescription();
  }
}
