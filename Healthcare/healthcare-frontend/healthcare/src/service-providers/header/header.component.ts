import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { RouterService } from 'src/app/healthroute/Service/router.service';
import { ProvidersService } from '../services/providers.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css'],
})
export class HeaderComponent implements OnInit {
  uname: string = '';
  isNotificationsOpen: boolean = false;
  isLoggedIn: boolean = false;
  notificationsCount: number = 0;
  role: string | null = '';

  constructor(
    private router: RouterService,
    private api: ProvidersService,
    private snackbar: MatSnackBar
  ) {}

  ngOnInit(): void {
    this.role = sessionStorage.getItem('Role')!;
    console.log('role is');
    console.log(this.role);
    this.uname = sessionStorage.getItem('name')!;
    console.log('--Header Init---');
    console.log(sessionStorage.getItem('bearerToken'));
    console.log(this.isLoggedIn);
    console.log('++++++++++++++');
    if (sessionStorage.getItem('bearerToken') == null) {
      this.isLoggedIn = false;
    } else {
      this.isLoggedIn = true;
    }
    // this.api.getAllDoctors().subscribe((res) => {
    //   console.log(res);
    // });
    // this.api
    //   .getServiceProvidersByEmail('g.adhikar@gmail.com')
    //   .subscribe((res) => {
    //     console.log(res);
    //   });
    this.role = sessionStorage.getItem('Role');
  }

  updateNoOfNotifications(noOfNotifications: number) {
    this.notificationsCount = noOfNotifications;
  }

  openNotifications() {
    this.isNotificationsOpen = true;
  }

  closeNotifications() {
    this.isNotificationsOpen = false;
  }
  goToMedications() {
    this.router.openMeds();
  }
  goToProfile() {
    let role = sessionStorage.getItem('Role');
    if (role === 'ServiceProvider') {
      //go to SP profilepage using sessionStorage id
      this.router.openProviderProfile();
    } else if (role === 'Patient') {
      //go to Patient profilepage using sessionStorage id
      this.router.openPatientProfile();
    } else {
      this.router.openLanding();
    }
  }
  goHome() {
    this.router.openLanding();
  }

  goSearchProviders() {
    this.router.openSearchProviders();
  }

  goPatientProfile() {
    this.router.openPatientProfile();
  }

  goMedicalHistory() {
    this.router.openMedicalHistory();
  }

  goToReviews() {
    if(this.role==='ServiceProvider'){
      this.router.openViewAllRevieweeReviews(sessionStorage.getItem('userId')!);
    }
  }
  goToViewRevieweeReviews() {
    this.router;
  }
  goToCalendar() {
    let role = sessionStorage.getItem('Role');
    if (role === 'ServiceProvider') {
      this.router.openProviderCalendar();
    } else if (role === 'Patient') {
      this.router.openPatientDash();
    }
  }
  goToAllBookings() {}

  goToPaymentsDashboard() {
    this.router.openPaymentDashboard();
  }

  logoutUser() {
    console.log(this.isLoggedIn);
    if (this.isLoggedIn) {
      console.log('Logging Out');
      this.isLoggedIn = false;
      this.role = '';
      sessionStorage.clear();
      this.snackbar.open('Logged Out', 'X', {
        duration: 1000,
      });
    }
    this.router.openLogin();
  }
}
