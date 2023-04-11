import { Component, OnInit } from '@angular/core';
import { RouterService } from 'src/app/healthroute/Service/router.service';
import { CalendarOptions } from '@fullcalendar/core'; // useful for typechecking
import interactionPlugin from '@fullcalendar/interaction';
import dayGridPlugin from '@fullcalendar/daygrid';
import timeGridPlugin from '@fullcalendar/timegrid';
import listPlugin from '@fullcalendar/list';
import { MatDialog } from '@angular/material/dialog';
import { BookingdbService } from 'src/app/booking/service/bookingdb.service';
import { Booking } from 'src/app/booking/model/Booking';
import { Booking as ProviderBooking } from 'src/service-providers/models/booking';
import { PatientBookingDetailsComponent } from 'src/app/patient/patient-booking-details/patient-booking-details.component';
import { PatientBookingDialogResult } from 'src/app/patient/patient-booking-details/models/PatientBookingDialogResult';
import { PatientserviceService } from 'src/app/patient/patientservice.service';
import { HttpHeaders } from '@angular/common/http';
import { ProvidersService } from 'src/service-providers/services/providers.service';
import { TimeSlot } from 'src/app/booking/model/TimeSlot';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-patientdashboard',
  templateUrl: './patientdashboard.component.html',
  styleUrls: ['./patientdashboard.component.css'],
})
export class PatientdashboardComponent implements OnInit {
  pageTitle = 'Patient Dashboard';
  uname: string = '';
  booking: Booking = new Booking();
  currentDate!: Date;
  calendarOptions: CalendarOptions = {
    plugins: [interactionPlugin, dayGridPlugin, timeGridPlugin, listPlugin],
    headerToolbar: {
      left: 'prev,next',
      center: 'title',
      right: 'dayGridMonth,timeGridWeek,timeGridDay,listWeek',
    },
    initialView: 'listWeek',
    eventClick: this.handleEventClick.bind(this), // bind is important!
  };
  bookings: any[] = [];

  constructor(
    private router: RouterService,
    private bookingService: BookingdbService,
    private patientService: PatientserviceService,
    private providersService: ProvidersService,
    public dialog: MatDialog,
    private datePipe: DatePipe
  ) {
    this.uname = sessionStorage.getItem('name')!;
  }

  ngOnInit(): void {
    this.loadPatientBookings();
  }
  emergency() {
    console.log('Call Emergency');
    if (
      confirm(
        'This is For Emergencies. Please confirm that you are in an emergency and a Doctor will be with you shortly'
      )
    ) {
      this.currentDate = new Date();
      console.log(this.datePipe.transform(this.currentDate, 'HH:mm'));
      const day = this.datePipe.transform(this.currentDate, 'yyyy-MM-dd');
      this.booking.isEmergency = true;
      this.booking.patientId = sessionStorage.getItem('userId')!;
      this.booking.location =
        'https://us06web.zoom.us/w/82930493207?tk=KoPTQa6-FA2CkDxSmfawkVc0EzfSQAvGTrEJZjTeAJ8.DQMAAAATTwrnFxZxYTFkX1Q0cFFtZXRScENvRDBEMDhBAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA#success';
      this.booking.paymentToken = '';
      this.booking.timeSlot = new TimeSlot();
      this.booking.timeSlot.bookingDate = day?.toString();
      this.booking.status = 1;
      if (this.datePipe.transform(this.currentDate, 'mm')! >= '30') {
        this.booking.timeSlot.bookingTime =
          this.datePipe.transform(this.currentDate, 'HH') + ':30:00';
      } else {
        this.booking.timeSlot.bookingTime =
          this.datePipe.transform(this.currentDate, 'HH') + ':00:00';
      }
      this.booking.timeSlot.duration = 30;
      this.booking.recurrenceInterval = 0;
      this.booking.status = 1;
      this.booking.subject =
        'Emergency Appointment for: ' + sessionStorage.getItem('name');

      this.providersService.getAllDoctors().subscribe((res) => {
        console.log(res);
        let spId = '';
        res.forEach((sp) => {
          if (
            sp.bookings.filter(
              (bk) =>
                this.datePipe.transform(bk.bookingDate, 'yyyy-MM-dd HH:mm') ==
                  this.datePipe.transform(
                    this.currentDate,
                    'yyyy-MM-dd HH:30'
                  ) ||
                this.datePipe.transform(bk.bookingDate, 'yyyy-MM-dd HH:mm') ==
                  this.datePipe.transform(this.currentDate, 'yyyy-MM-dd HH:00')
            ).length == 0
          ) {
            spId = sp.id!;
          }
        });
        console.log(spId);
        if (spId === '') {
          alert(
            'No Doctors are available wait for the next available agent or dial your local emergency number'
          );
        } else {
          this.booking.serviceProviderId = spId;
          this.providersService
            .addNotificationForProvider(
              this.booking.serviceProviderId!,
              this.booking.subject!
            )
            .subscribe(
              (res) => {
                console.log('Notification added to Provider');
              },
              (err) => {
                console.log(err);
                console.log('Notification Not added to Provider');
              }
            );
          this.bookingService.createBooking(this.booking).subscribe(
            (res) => {
              console.log('booking Saved');
              let pBooking: ProviderBooking;
              if (this.currentDate.getMinutes() >= 30) {
                this.currentDate.setMinutes(30);
              } else {
                this.currentDate.setMinutes(0);
              }
              pBooking = {
                bookingId: res.id,
                bookingDate: this.currentDate,
                duration: 30,
              };
              this.providersService
                .addBookingForProvider(
                  this.booking.serviceProviderId!,
                  pBooking
                )
                .subscribe(
                  (res) => {
                    console.log('Booking Added to Provider');
                    this.router.openInProgressMeeting(pBooking.bookingId!);
                  },
                  (err) => {
                    console.log(err);
                    console.log('Booking Not Added to Provider');
                  }
                );
            },
            (err) => {
              console.log(err);
              console.log('Booking Not Saved');
            }
          );
        }
      });
      console.log('Booking information');
      console.log(this.booking);
      //   this.booking.serviceProviderId="0";
    }
  }

  // Loads the patient's bookings
  loadPatientBookings() {
    const patientId = sessionStorage.getItem('userId');
    console.log(patientId);

    if (!patientId) {
      console.log('No patient id found');
      return;
    }

    /* 
    let patientName = '';
    let headers = new HttpHeaders().set(
      'Authorization',
      `Bearer ${sessionStorage.getItem('bearerToken')}`
    );
    this.patientService
            .getPatientById(headers, patientId)!
            .subscribe((res) => {
              patientName = res.firstName + ' ' + res.lastName; */

    this.bookingService
      .viewBookingsByPatient(patientId)
      .subscribe((bookingData) => {
        const bookings: any[] = [];
        if (bookingData.length === 0) {
          this.bookings = bookings;
          this.calendarOptions.events = bookings;
          return;
        }
        for (const booking of bookingData) {
          // Add duration minutes to the bookingDate
          const startDateTime = new Date(
            booking.timeSlot?.bookingDate! +
              ' ' +
              booking.timeSlot?.bookingTime!
          );
          const endDateTime = new Date(
            booking.timeSlot?.bookingDate! +
              ' ' +
              booking.timeSlot?.bookingTime!
          );
          endDateTime.setMinutes(
            endDateTime.getMinutes() + booking.timeSlot?.duration!
          );

          bookings.push({
            id: booking.id,
            title: booking.subject,
            start: startDateTime,
            end: endDateTime,
          });

          console.log(bookings);
        }
        console.log(bookings);
        this.bookings = bookings;
        // Load the events to the calendar
        this.calendarOptions.events = bookings;
      });
  }

  // Handle the event click
  handleEventClick(arg: any) {
    const selectedBookingId = arg.event.id;
    console.log(`Booking Id: ${selectedBookingId}`);

    let bookingDetails: Booking;

    this.bookingService.viewBooking(selectedBookingId).subscribe((res) => {
      bookingDetails = { ...res };
      this.openDialog(bookingDetails);
    });
  }

  // Opens the booking details dialog
  openDialog(details: Booking): void {
    const dialogRef = this.dialog.open(PatientBookingDetailsComponent, {
      data: details,
    });

    // Returns confirm/update based on which button is clicked
    dialogRef.afterClosed().subscribe((result) => {
      if (result === PatientBookingDialogResult.cancel) {
        console.log('Cancel booking');
        // Delete the booking from booking table
        this.bookingService.deleteBooking(details.id!).subscribe((_res) => {
          console.log(`Booking ${details.id} was cancelled`);
          this.loadPatientBookings();

          // Delete the booking from the providers table
          this.providersService
            .deleteBookingForProvider(details.serviceProviderId!, details.id!)
            .subscribe((_res) => {
              console.log(
                `Booking ${details.id} was deleted from provider table`
              );
              // Send notification to provider
              this.providersService
                .addNotificationForProvider(
                  details.serviceProviderId!,
                  `Your booking on ${details.timeSlot?.bookingDate} @ ${details.timeSlot?.bookingTime} was cancelled by the patient.`
                )
                .subscribe((_res) => {
                  console.log(
                    `Notification sent to provider ${details.serviceProviderId}`
                  );
                });
            });
        });
      } else if (result === PatientBookingDialogResult.join_meeting) {
        console.log('Join meeting');

        // Send notifaction to provider that patient has joined the meeting
        const jwtToken = sessionStorage.getItem('bearerToken');
        const headers = new HttpHeaders().set(
          'Authorization',
          `Bearer ${jwtToken}`
        );
        this.patientService
          .getPatientById(headers, details.patientId!)
          .subscribe((_res) => {
            const patientFromDb = _res;
            this.providersService
              .addNotificationForProvider(
                details.serviceProviderId!,
                `${patientFromDb?.firstName} ${patientFromDb?.lastName} has joined the meeting`
              )
              .subscribe((_res) => {
                console.log(
                  `Notification sent to provider ${details.serviceProviderId}`
                );
              });
          });
      } else {
        console.log('The dialog was closed');
      }
    });
  }

  goSearchProviders() {
    this.router.openSearchProviders();
  }
}
