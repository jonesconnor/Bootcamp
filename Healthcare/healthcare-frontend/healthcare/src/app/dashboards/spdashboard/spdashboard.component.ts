import { Component } from '@angular/core';
import { CalendarOptions } from '@fullcalendar/core'; // useful for typechecking
import interactionPlugin from '@fullcalendar/interaction';
import dayGridPlugin from '@fullcalendar/daygrid';
import timeGridPlugin from '@fullcalendar/timegrid';
import listPlugin from '@fullcalendar/list';
import { Booking } from 'src/app/booking/model/Booking';
import { RouterService } from 'src/app/healthroute/Service/router.service';
import { HttpHeaders } from '@angular/common/http';
import { ProvidersService } from 'src/service-providers/services/providers.service';
import { BookingdbService } from 'src/app/booking/service/bookingdb.service';
import { PatientserviceService } from 'src/app/patient/patientservice.service';
import { MatDialog } from '@angular/material/dialog';
import { lastValueFrom } from 'rxjs';

import {
  BookingDetailsData,
  DialogResult,
} from 'src/service-providers/models/booking-details-data';
import { BookingDetailsComponent } from 'src/service-providers/booking-details/booking-details.component';
import { Patient } from 'src/app/patient/Models/patient.model';
import { ServiceProvider } from 'src/service-providers/models/serviceprovider';

@Component({
  selector: 'app-spdashboard',
  templateUrl: './spdashboard.component.html',
  styleUrls: ['./spdashboard.component.css'],
})
export class SPdashboardComponent {
  STATUS = ['Pending', 'Confirmed', 'Updated', 'Complete'];
  userRole!: string | null;
  pageTitle = 'Service Provider Dashboard';
  dialogResult = DialogResult;
  booking!: Booking;
  bookings: any[] = [];
  provider!: ServiceProvider;
  patientid = '';
  patient!: Patient;
  showToConfirmPanel = false;

  constructor(
    private providerApi: ProvidersService,
    private bookingApi: BookingdbService,
    private patientApi: PatientserviceService,
    public dialog: MatDialog,
    private route: RouterService
  ) {}
  calendarOptions: CalendarOptions = {
    plugins: [interactionPlugin, dayGridPlugin, timeGridPlugin, listPlugin],
    headerToolbar: {
      left: 'prev,next',
      center: 'title',
    },
    initialView: 'timeGridDay',
    eventClick: this.handleEventClick.bind(this), // bind is important!
  };

  ngOnInit(): void {
    this.userRole = sessionStorage.getItem('Role');
    this.loadProviderSchedule();
    this.getBookingStatus();
  }

  async getBookingStatus() {
    const providerEmail = sessionStorage.getItem('emailId');
    const response = this.providerApi.getServiceProvidersByEmail(
      providerEmail!
    );
    this.provider = await lastValueFrom(response);

    this.bookingApi
      .viewBookingsByProvider(this.provider.id!)
      .subscribe((res) => {
        console.log(res);
        this.bookings = res;
        if (this.bookings.some((x) => x.status === 0)) {
          this.showToConfirmPanel = true;
        } else {
          this.showToConfirmPanel = false;
        }
      });
  }

  // Load the provider's bookings
  async loadProviderSchedule() {
    const providerEmail = sessionStorage.getItem('emailId');
    const response = this.providerApi.getServiceProvidersByEmail(
      providerEmail!
    );
    this.provider = await lastValueFrom(response);

    const providerId = this.provider.id;
    console.log(providerId);

    if (providerId) {
      this.providerApi
        .getAllBookingsForProvider(providerId)
        .subscribe(async (data) => {
          const bookings: any[] = [];

          for (const booking of data) {
            // Get booking details from booking table
            this.bookingApi.viewBooking(booking.bookingId!).subscribe((res) => {
              const bookingFromDb = res;
              console.log(bookingFromDb);

              // Retrieve the patient details form Db
              const jwtToken = sessionStorage.getItem('bearerToken');
              this.patientApi
                .getPatientById(
                  new HttpHeaders().set('Authorization', `Bearer ${jwtToken}`),
                  bookingFromDb.patientId + ''
                )
                .subscribe((res) => {
                  const patientFromDb = res;
                  // Add duration minutes to the bookingDate
                  const endTime = new Date(booking.bookingDate);
                  endTime.setMinutes(endTime.getMinutes() + booking.duration);

                  bookings.push({
                    id: booking.bookingId,
                    title:
                      patientFromDb.firstName + ' - ' + patientFromDb.lastName,
                    start: this.convertToLocalDateTime(booking.bookingDate),
                    end: this.convertToLocalDateTime(endTime),
                  });

                  if (data.indexOf(booking) === data.length - 1) {
                    console.log(bookings);
                    // Load the events to the calendar
                    this.calendarOptions.events = bookings;
                    this.calendarOptions.events = bookings;
                  }
                });
            });
          }
        });
    }
  }

  // Handles the clicking of a booking on the calendar
  handleEventClick(arg: any) {
    // console.log(arg.event.id);
    // TODO: Implement the logic to retrieve the patient details from the database
    let bookingDetails: BookingDetailsData = {
      patientName: 'unknown',
      bookingDate: 'unknown',
      bookingTime: 'unknown',
      duration: 0,
      isEmergency: false,
      subject: 'unknown',
      location: 'unknown',
      status: 'unknown',
    };
    this.bookingApi.viewBooking(arg.event.id).subscribe((res) => {
      this.booking = res;

      bookingDetails.id = this.booking.id!;
      bookingDetails.bookingDate = this.booking.timeSlot!.bookingDate!;
      bookingDetails.bookingTime = this.booking.timeSlot?.bookingTime!;
      bookingDetails.duration = this.booking.timeSlot?.duration!;
      bookingDetails.isEmergency = this.booking.isEmergency!;
      bookingDetails.subject = this.booking.subject!;
      bookingDetails.location = this.booking.location!;
      bookingDetails.status = this.STATUS[this.booking.status!];

      const jwtToken = sessionStorage.getItem('bearerToken');
      this.patientApi
        .getPatientById(
          new HttpHeaders().set('Authorization', `Bearer ${jwtToken}`),
          this.booking.patientId + ''
        )
        .subscribe((res) => {
          this.patient = res;
          bookingDetails.patientName =
            this.patient.firstName + ' ' + this.patient.lastName;

          this.openDialog(bookingDetails);
        });

      //this.patient = await lastValueFrom(response2);
    });
    // this.booking = await lastValueFrom(response);
  }

  // Opens the booking details dialog
  openDialog(details: BookingDetailsData): void {
    const dialogRef = this.dialog.open(BookingDetailsComponent, {
      data: details,
    });

    // Returns confirm/update based on which button is clicked
    dialogRef.afterClosed().subscribe((result) => {
      if (result === DialogResult.update) {
        console.log('Update booking');
        this.route.openUpdatebooking(this.booking.id);
      } else if (result === DialogResult.confirm) {
        console.log('Confirm booking');
        this.booking.status = 1;
        let ind = this.bookings.findIndex((b) => b.id == this.booking.id);
        this.bookings[ind].status = 1;
        this.bookingApi.updateBooking(this.booking).subscribe();

        ind = this.booking.timeSlot!.bookingTime!.lastIndexOf(':00');
        let readableDate =
          this.booking.timeSlot!.bookingDate! +
          ' ' +
          this.booking.timeSlot!.bookingTime?.substring(0, ind);
        console.log(readableDate);
        const jwtToken = sessionStorage.getItem('bearerToken');
        this.patientApi
          .addNotification(
            new HttpHeaders().set('Authorization', `Bearer ${jwtToken}`),
            this.booking.patientId!,
            'Appointment on ' + readableDate + ' has been confirmed.'
          )
          .subscribe();
        this.getBookingStatus();
        // TODO send notifaction to patient
      } else if (result === DialogResult.start) {
        console.log('Start booking');
        let ind = this.bookings.findIndex((b) => b.id == this.booking.id);
        let temp = (this.booking.timeSlot!.bookingDate! + '').substring(0, ind);
        const jwtToken = sessionStorage.getItem('bearerToken');
        let headers = new HttpHeaders().set(
          'Authorization',
          `Bearer ${jwtToken}`
        );
        this.patientApi
          .addNotification(
            headers,
            this.booking.patientId!,
            'Your meeting at ' + temp + ' has started.'
          )
          .subscribe((_res) => {
            console.log('Notification has been sent to the patient');
          });
      } else {
        console.log('The dialog was closed');
      }
    });
  }

  // The bookings are stored in UTC format in the database
  // This function converts the UTC date to local date
  convertToLocalDateTime(inputDate: Date) {
    const date = new Date(inputDate);
    const offset = date.getTimezoneOffset();
    date.setMinutes(date.getMinutes() + offset);
    return date;
  }
}
