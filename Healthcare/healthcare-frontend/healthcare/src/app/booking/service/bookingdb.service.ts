import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Booking } from '../model/Booking';
import { TimeSlot } from '../model/TimeSlot';

@Injectable({
  providedIn: 'root',
})
export class BookingdbService {
  token: any;

  constructor(private httpcli: HttpClient) {
    this.token = sessionStorage.getItem('bearerToken');
  }

  createBooking(booking: Booking): Observable<Booking> {
    return this.httpcli.post('http://localhost:9000/bookings/create', booking, {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
    });
  }

  updateBooking(booking : Booking) : Observable<Booking>
  {
   return this.httpcli.put('http://localhost:9000/bookings/update',booking,{
    headers: new HttpHeaders({"Content-Type":"application/json"})//.set('Authorization','Bearer '+this.token)
  })
  }


  updateBookingTimeSlot(id: String, timeslot: TimeSlot): Observable<Booking> {
    return this.httpcli.put(
      'http://localhost:9000/bookings/update/timeslot/' + id,
      timeslot,
      {
        headers: new HttpHeaders({ 'Content-Type': 'application/json' }), //.set('Authorization','Bearer '+this.token)
      }
    );
  }

  deleteBooking(bookingId: String): Observable<any> {
    return this.httpcli.delete(
      'http://localhost:9000/bookings' + '/' + bookingId
    );
  }

  viewAllBooking(): Observable<Array<Booking>> {
    return this.httpcli.get<Array<Booking>>(
      'http://localhost:9000/bookings/all'
    );
  }

  viewBooking(bookingId: String): Observable<Booking> {
    return this.httpcli.get(
      'http://localhost:9000/bookings/view' + '/' + bookingId
    );
  }

  viewBookingsByPatient(value: Object): Observable<Array<Booking>> {
    return this.httpcli.get<Array<Booking>>(
      'http://localhost:9000/bookings/patient/' + value
    );
  }

  viewBookingsByProvider(value: Object): Observable<Array<Booking>> {
    return this.httpcli.get<Array<Booking>>(
      'http://localhost:9000/bookings/provider/' + value
    );
  }

  viewBookingsByDate(value: Object): Observable<Array<Booking>> {
    return this.httpcli.get<Array<Booking>>(
      'http://localhost:9000/bookings/date/' + value
    );
  }

  viewBookingsByStatus(value: Object): Observable<Array<Booking>> {
    return this.httpcli.get<Array<Booking>>(
      'http://localhost:9000/bookings/status/' + value
    );
  }
  viewBookingsByLocation(value: Object): Observable<Array<Booking>> {
    return this.httpcli.get<Array<Booking>>(
      'http://localhost:9000/bookings/location/' + value
    );
  }

  viewBookingsByEmergency(value: Object): Observable<Array<Booking>> {
    return this.httpcli.get<Array<Booking>>(
      'http://localhost:9000/bookings/emergency/' + value
    );
  }

  viewBookingsByPaymentToken(value: Object): Observable<Array<Booking>> {
    return this.httpcli.get<Array<Booking>>(
      'http://localhost:9000/bookings/payment/' + value
    );
  }

  viewBookingsByPatientAndProvider(
    patientvalue: Object,
    providervalue: Object
  ): Observable<Array<Booking>> {
    return this.httpcli.get<Array<Booking>>(
      'http://localhost:9000/bookings/patient/' +
        patientvalue +
        '/provider/' +
        providervalue
    );
  }
  viewBookingsByPatientAndDate(
    patientvalue: Object,
    datevalue: Object
  ): Observable<Array<Booking>> {
    return this.httpcli.get<Array<Booking>>(
      'http://localhost:9000/bookings/patient/' +
        patientvalue +
        '/date/' +
        datevalue
    );
  }
  viewBookingsByProviderAndDate(
    providervalue: Object,
    datevalue: Object
  ): Observable<Array<Booking>> {
    return this.httpcli.get<Array<Booking>>(
      'http://localhost:9000/bookings/provider/' +
        providervalue +
        '/date/' +
        datevalue
    );
  }
}
