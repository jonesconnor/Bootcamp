import { Component, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { RouterService } from 'src/app/healthroute/Service/router.service';

import {
  BookingDetailsData,
  DialogResult,
} from '../models/booking-details-data';

@Component({
  selector: 'app-booking-details',
  templateUrl: './booking-details.component.html',
  styleUrls: ['./booking-details.component.css'],
})
export class BookingDetailsComponent {
  dialogResult = DialogResult;

  constructor(
    public dialogRef: MatDialogRef<BookingDetailsComponent>,
    private router: RouterService,
    @Inject(MAT_DIALOG_DATA) public data: BookingDetailsData
  ) {}

  onGoBackClick(): void {
    this.dialogRef.close(this.dialogResult.close);
  }

  openMeetingInProgress(data: BookingDetailsData) {
    this.dialogRef.close(this.dialogResult.start);
    this.router.openInProgressMeeting(data.id!);
  }
}
