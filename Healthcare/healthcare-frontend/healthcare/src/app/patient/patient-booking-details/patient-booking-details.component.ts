import { Component, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Booking } from 'src/app/booking/model/Booking';
import { RouterService } from 'src/app/healthroute/Service/router.service';
import { ProvidersService } from 'src/service-providers/services/providers.service';
import { PatientBookingDialogResult } from './models/PatientBookingDialogResult';

@Component({
  selector: 'app-patient-booking-details',
  templateUrl: './patient-booking-details.component.html',
  styleUrls: ['./patient-booking-details.component.css'],
})
export class PatientBookingDetailsComponent {
  dialogResult = PatientBookingDialogResult;
  STATUS = ['Pending', 'Confirmed', 'Updated', 'Complete'];
  showMeetingLink = false;
  providerName = '';

  constructor(
    public dialogRef: MatDialogRef<PatientBookingDetailsComponent>,
    private router: RouterService,
    private providersService: ProvidersService,
    @Inject(MAT_DIALOG_DATA) public data: Booking
  ) {
    this.providersService
      .getServiceProviderById(data.serviceProviderId!)
      .subscribe((res) => {
        this.providerName = res.firstName + ' ' + res.lastName;
      });
  }

  onGoBackClick(): void {
    this.dialogRef.close(this.dialogResult.close);
  }

  onJoinMeetingClick() {
    this.showMeetingLink = true;
  }

  openMeetingInProgress(data: Booking) {
    this.dialogRef.close(this.dialogResult.join_meeting);
    this.router.openInProgressMeeting(data.id!);
  }

  redirectToPayment(bookingId: string) {
    this.router.openPayment(bookingId);
    this.onGoBackClick();
  }
}
