import { Component, Input, Output, EventEmitter } from '@angular/core';
import { FormGroup } from '@angular/forms';
import {
  AbstractControl,
  FormBuilder,
  FormControl,
  FormGroupDirective,
  NgForm,
  Validators,
} from '@angular/forms';
import { Booking } from '../model/Booking';
import { TimeSlot } from '../model/TimeSlot';
import { BookingdbService } from '../service/bookingdb.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ProvidersService } from 'src/service-providers/services/providers.service';
import { ServiceProvider } from 'src/service-providers/models/serviceprovider';
import { Booking as ProviderBooking } from 'src/service-providers/models/booking';
import { PatientserviceService } from 'src/app/patient/patientservice.service';
import { HttpHeaders } from '@angular/common/http';
import { lastValueFrom } from 'rxjs';
import { RouterService } from 'src/app/healthroute/Service/router.service';

@Component({
  selector: 'app-create',
  templateUrl: './create.component.html',
  styleUrls: ['./create.component.css'],
})
export class CreateComponent {
  form: FormGroup;
  message = '';
  options = '';
  types = ['daily', 'weekly', 'monthly'];
  times = [
    '08:00',
    '08:30',
    '09:00',
    '09:30',
    '10:00',
    '10:30',
    '11:00',
    '11:30',
    '12:00',
    '12:30',
    '13:00',
    '13:30',
    '14:00',
    '14:30',
    '15:00',
    '15:30',
    '16:00',
    '16:30',
    '17:00',
    '17:30',
    '18:00',
    '18:30',
    '19:00',
    '19:30',
    '20:00',
    '20:30',
  ];
  booking: Booking = new Booking();
  @Input() provider!: ServiceProvider;
  @Output() state = new EventEmitter<boolean>();
  check = 0;
  minDate: Date;

  constructor(
    private router: RouterService,
    private formBuilder: FormBuilder,
    private serv: BookingdbService,
    private snackobj: MatSnackBar,
    private provservice: ProvidersService,
    private patservice: PatientserviceService
  ) {
    this.minDate = new Date();
    this.booking.location =
      'https://us06web.zoom.us/w/82930493207?tk=KoPTQa6-FA2CkDxSmfawkVc0EzfSQAvGTrEJZjTeAJ8.DQMAAAATTwrnFxZxYTFkX1Q0cFFtZXRScENvRDBEMDhBAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA#success';
    console.log(this.booking.location);
    this.form = this.formBuilder.group({
      location: [{ value: '', disabled: true }, [Validators.required]],
      subject: ['', [Validators.required, this.justSpaceValidator]],
      bookingDate: ['', [Validators.required]],
      bookingTime: ['', [Validators.required]],
      recurrenceType: ['', [Validators.required]],
      recurrenceEndDate: ['', [Validators.required]],
    });
  }

  onSubmit() {
    if (
      this.check &&
      (this.form.get('recurrenceType')?.hasError('required') ||
        this.form.get('recurrenceEndDate')?.hasError('required'))
    ) {
      this.message =
        'Repeating appointments must specify how often and an end date.';
    } else {
      this.message = '';
      this.booking.isEmergency = false;

      const patientEmail = sessionStorage.getItem('emailId');
      const jwtToken = sessionStorage.getItem('bearerToken');
      this.patservice
        .getPatientByEmail(
          new HttpHeaders().set('Authorization', `Bearer ${jwtToken}`),
          patientEmail!
        )
        .subscribe((res) => {
          let patient = res;

          this.booking.patientId = patient.id;

          this.booking.serviceProviderId = this.provider.id;
          this.booking.paymentToken = '';
          this.booking.status = 0;
          this.booking.subject = this.form.get('subject')?.value;

          //this.booking.location=this.form.get("location")?.value
          //console.log(this.booking.location);

          this.booking.timeSlot = new TimeSlot();
          this.booking.timeSlot.bookingDate =
            this.form.get('bookingDate')?.value;
          this.booking.timeSlot.bookingTime =
            this.form.get('bookingTime')?.value + ':00';
          this.booking.timeSlot.duration = 30;
          this.booking.recurrenceType = this.form.get('recurrenceType')?.value;
          this.booking.recurrenceEndDate =
            this.form.get('recurrenceEndDate')?.value;
          this.booking.recurrenceInterval = this.check;

          if (this.check) {
            this.createRecurring();
          } else {
            this.serv.createBooking(this.booking).subscribe((res) => {
              this.booking = res;
              console.log(this.booking);

              if (this.booking != undefined) {
                this.successsnack();
                this.clearForm();
                this.bookingSuccess(this.booking);
              } else {
                this.failsnack();
              }
            });
          }
        });
    }
  }

  bookingSuccess(booked: Booking) {
    if (this.provider.id != undefined) {
      let ind = booked.timeSlot!.bookingTime?.lastIndexOf(':00');
      let readableDate =
        booked.timeSlot!.bookingDate! +
        ' ' +
        booked.timeSlot!.bookingTime?.substring(0, ind);
      console.log(`Readable date: ${readableDate}`);

      const utcDate = this.convertToUTC(
        booked.timeSlot!.bookingDate! + ' ' + booked.timeSlot!.bookingTime!
      );

      console.log(`UTC date: ${utcDate}`);
      let newBooking: ProviderBooking = {
        bookingId: booked.id,
        bookingDate: utcDate,
        duration: booked.timeSlot!.duration!,
      };
      this.provider.bookings.push(newBooking);
      this.updateProvider(newBooking, readableDate);
    }
  }

  convertToUTC(dateTimeString: string): Date {
    let formattedDate = new Date(dateTimeString);
    const offsetInMinutes = formattedDate.getTimezoneOffset();
    const utcDate = new Date(formattedDate.getTime() - offsetInMinutes * 60000);
    return utcDate;
  }

  async updateProvider(newBooking: ProviderBooking, truncdate: String) {
    if (this.provider.id != undefined) {
      console.log(truncdate);
      const resp = this.provservice.addBookingForProvider(
        this.provider.id,
        newBooking
      );
      await lastValueFrom(resp);

      const resp2 = this.provservice.addNotificationForProvider(
        this.provider.id,
        'New appointment requested on ' +
          truncdate +
          '. Please confirm the appointment in your Dashboard'
      );
      await lastValueFrom(resp2);
    }
  }

  clearForm() {
    this.form.reset();
  }

  myFilter = (d: Date | null): boolean => {
    const day = d || new Date();
    // Prevent past days to be selected

    if (this.provider.bookings == null || this.provider.bookings == undefined) {
      return day > this.minDate;
    }
    let alreadyBooked = this.provider.bookings.map((b) => {
      let temp = new Date(b.bookingDate);
      return new Date(
        temp.getFullYear(),
        temp.getMonth(),
        temp.getDate(),
        0,
        0,
        0,
        0
      ).toDateString();
    });
    return day > this.minDate && !alreadyBooked.includes(day.toDateString());
  };

  createRecurring() {
    let end = this.form.get('recurrenceEndDate')?.value;
    let start = this.form.get('bookingDate')?.value;
    console.log(end);
    console.log(start);
    if (end <= start) {
      this.failsnack();
    }
    switch (this.form.get('recurrenceType')?.value) {
      case 'daily': {
        while (start <= end) {
          this.serv.createBooking(this.booking).subscribe(
            (res) => {
              this.successsnack();
              this.clearForm();
              this.bookingSuccess(res);
            },
            (err) => {
              this.failsnack();
            }
          );
          start.setDate(start.getDate() + 1);
          if (this.booking.timeSlot == undefined) {
            break;
          }
          this.booking.timeSlot.bookingDate = start;
        }
        break;
      }
      case 'weekly': {
        while (start <= end) {
          this.serv.createBooking(this.booking).subscribe(
            (res) => {
              this.clearForm();
              this.successsnack();
              this.bookingSuccess(res);
            },
            (err) => {
              this.failsnack();
            }
          );
          start.setDate(start.getDate() + 7);
          //console.log(start)
          if (this.booking.timeSlot == undefined) {
            break;
          }
          this.booking.timeSlot.bookingDate = start;
        }

        break;
      }
      case 'monthly': {
        while (start <= end) {
          this.serv.createBooking(this.booking).subscribe(
            (res) => {
              this.successsnack();
              this.clearForm();
              this.bookingSuccess(res);
            },
            (err) => {
              this.failsnack();
            }
          );
          start.setMonth(start.getMonth() + 1);
          if (this.booking.timeSlot == undefined) {
            break;
          }
          this.booking.timeSlot.bookingDate = start;
        }
        break;
      }
      default: {
        this.failsnack();
        break;
      }
    }
  }

  successsnack() {
    this.snackobj.open('Booking successfully created.', '', {
      duration: 2000,
    });
  }

  failsnack() {
    this.snackobj.open(
      'Failed to create booking. Please check all necessary information is correct',
      '',
      {
        duration: 2000,
      }
    );
  }

  closeComponent() {
    this.state.emit(false);
  }

  public justSpaceValidator(control: FormControl) {
    const isWhitespace = (control.value || '').trim().length === 0;
    const isValid = !isWhitespace;
    return isValid ? null : { whitespace: true };
  }

  goToSPReviews(spId: string) {
    this.router.openViewAllRevieweeReviews(spId);
  }
}
