import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { CalendarOptions } from '@fullcalendar/core'; // useful for typechecking
import interactionPlugin from '@fullcalendar/interaction';
import dayGridPlugin from '@fullcalendar/daygrid';
import timeGridPlugin from '@fullcalendar/timegrid';
import listPlugin from '@fullcalendar/list';
import { Booking } from 'src/app/booking/model/Booking';
import { lastValueFrom } from 'rxjs';
import { HttpHeaders } from '@angular/common/http';
import { ProvidersService } from '../services/providers.service';
import { BookingdbService } from 'src/app/booking/service/bookingdb.service';
import { BookingDetailsComponent } from '../booking-details/booking-details.component';
import { PatientserviceService } from 'src/app/patient/patientservice.service';
import { RouterService } from 'src/app/healthroute/Service/router.service';
import {
  BookingDetailsData,
  DialogResult,
} from '../models/booking-details-data';

@Component({
  selector: 'app-schedule',
  templateUrl: './schedule.component.html',
  styleUrls: ['./schedule.component.css'],
})
export class ScheduleComponent implements OnInit {
  STATUS = ['Pending', 'Confirmed', 'Updated', 'Complete'];
  pageTitle = 'My Schedule';
  dialogResult = DialogResult;
  booking!: Booking;

  calendarOptions: CalendarOptions = {
    plugins: [interactionPlugin, dayGridPlugin, timeGridPlugin, listPlugin],
    headerToolbar: {
      left: 'prev,next today',
      center: 'title',
      right: 'dayGridMonth,timeGridWeek,timeGridDay,listWeek',
    },
    initialView: 'dayGridMonth',
    eventClick: this.handleEventClick.bind(this), // bind is important!
  };

  constructor(
    private providerApi: ProvidersService,
    private bookingApi: BookingdbService,
    private patientApi: PatientserviceService,
    public dialog: MatDialog,
    private route: RouterService
  ) {}

  ngOnInit(): void {
    this.loadProviderSchedule();
  }

  // Load the provider's bookings
  async loadProviderSchedule() {
    const providerEmail = sessionStorage.getItem('emailId');
    const response = this.providerApi.getServiceProvidersByEmail(
      providerEmail!
    );
    let provider = await lastValueFrom(response);

    const providerId = provider.id;

    console.log(providerId);

    if (providerId) {
      this.providerApi
        .getAllBookingsForProvider(providerId)
        .subscribe((data) => {
          const bookings: any[] = [];

          for (const booking of data) {
            // Add code to retrieve the patient details form Db
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
  async handleEventClick(arg: any) {
    // console.log(arg.event.id);
    // TODO: Implement the logic to retrieve  patient details from the database
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
    const response = this.bookingApi.viewBooking(arg.event.id);
    this.booking = await lastValueFrom(response);
    bookingDetails.id = this.booking.id;
    bookingDetails.bookingDate = this.booking.timeSlot!.bookingDate!;
    bookingDetails.bookingTime = this.booking.timeSlot?.bookingTime!;
    bookingDetails.duration = this.booking.timeSlot?.duration!;
    bookingDetails.isEmergency = this.booking.isEmergency!;
    bookingDetails.subject = this.booking.subject!;
    bookingDetails.location = this.booking.location!;
    bookingDetails.status = this.STATUS[this.booking.status!];

    const jwtToken = sessionStorage.getItem('bearerToken');
    const response2 = this.patientApi.getPatientById(
      new HttpHeaders().set('Authorization', `Bearer ${jwtToken}`),
      this.booking.patientId + ''
    );

    let patient = await lastValueFrom(response2);
    bookingDetails.patientName = patient.firstName + ' ' + patient.lastName;

    this.openDialog(bookingDetails);
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
        this.bookingApi.updateBooking(this.booking).subscribe();
        let ind = (this.booking.timeSlot!.bookingDate! + '').lastIndexOf(
          '00:00:00'
        );
        let temp = (this.booking.timeSlot!.bookingDate! + '').substring(0, ind);
        //const jwtToken = sessionStorage.getItem('bearerToken');
        this.patientApi
          .addNotification(
            new HttpHeaders(),
            this.booking.patientId!,
            'Appointment on ' + temp + ' has been confirmed.'
          )
          .subscribe();
        // TODO send notifaction to patient
      } else if (result === DialogResult.start) {
        //TODO redirect somewhere
        console.log('Start the meeting');
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
