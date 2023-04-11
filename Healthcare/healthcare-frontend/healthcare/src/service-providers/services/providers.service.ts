import { Injectable } from '@angular/core';
import {
  HttpClient,
  HttpErrorResponse,
  HttpHeaders,
} from '@angular/common/http';
import { Observable, map, throwError, catchError } from 'rxjs';
import { Booking } from '../models/booking';
import { ServiceProvider } from '../models/serviceprovider';

@Injectable({
  providedIn: 'root',
})
export class ProvidersService {
  API_BASE_URL = 'http://localhost:9099/api/serviceproviders';

  constructor(private http: HttpClient) {}

  // Get authorization header
  getAuthHeader(): HttpHeaders {
    const jwtToken = sessionStorage.getItem('bearerToken');
    console.log('jwtToken');
    console.log(jwtToken);
    return new HttpHeaders().set('Authorization', `Bearer ${jwtToken}`);
  }

  // Get all notifications for provider
  getNotificationsForProvider(providerId: string): Observable<string[]> {
    return this.http
      .get(`${this.API_BASE_URL}/${providerId}/notifications`, {
        headers: this.getAuthHeader(),
      })
      .pipe(
        map((res: any) => res),
        catchError(this.handleError)
      );
  }

  // Add a given notification to the list of notifications for a provider
  addNotificationForProvider(
    providerId: string,
    message: string
  ): Observable<any> {
    return this.http
      .patch(`${this.API_BASE_URL}/${providerId}/notifications/${message}`, {})
      .pipe(
        map((res: any) => res),
        catchError(this.handleError)
      );
  }

  // Delete notification with the given message from the list of notifications
  deleteNotificationForProvider(
    providerId: string,
    message: string
  ): Observable<any> {
    return this.http
      .patch(
        `${this.API_BASE_URL}/${providerId}/notifications/delete/${message}`,
        {},
        { headers: this.getAuthHeader() }
      )
      .pipe(
        map((res: any) => res),
        catchError(this.handleError)
      );
  }

  // Get all bookings for a provider
  getAllBookingsForProvider(providerId: string): Observable<Booking[]> {
    return this.http.get(`${this.API_BASE_URL}/${providerId}/bookings`).pipe(
      map((res: any) => res),
      catchError(this.handleError)
    );
  }

  // Get all bookings for a provider on a given date
  getBookingsForProviderOnGivenDate(
    providerId: string,
    bookingDate: string
  ): Observable<Booking[]> {
    return this.http
      .get(`${this.API_BASE_URL}/${providerId}/bookings/${bookingDate}`)
      .pipe(
        map((res: any) => res),
        catchError(this.handleError)
      );
  }

  // Add a new booking for a provider
  addBookingForProvider(
    providerId: string,
    newBooking: Booking
  ): Observable<any> {
    return this.http
      .patch(`${this.API_BASE_URL}/${providerId}/bookings`, newBooking)
      .pipe(
        map((res: any) => res),
        catchError(this.handleError)
      );
  }

  // Update a booking for a provider
  updateBookingForProvider(
    providerId: string,
    bookingId: string,
    updatedBooking: Booking
  ): Observable<any> {
    return this.http
      .patch(
        `${this.API_BASE_URL}/${providerId}/bookings/modify/${bookingId}`,
        updatedBooking,
        {
          headers: this.getAuthHeader(),
        }
      )
      .pipe(
        map((res: any) => res),
        catchError(this.handleError)
      );
  }

  // Delete a booking for a provider
  deleteBookingForProvider(
    providerId: string,
    bookingId: string
  ): Observable<any> {
    return this.http
      .patch(
        `${this.API_BASE_URL}/${providerId}/bookings/delete/${bookingId}`,
        {},
        {
          headers: this.getAuthHeader(),
        }
      )
      .pipe(
        map((res: any) => res),
        catchError(this.handleError)
      );
  }

  // Get all doctors
  getAllDoctors(): Observable<ServiceProvider[]> {
    return this.http.get(`${this.API_BASE_URL}/doctors`).pipe(
      map((res: any) => res),
      catchError(this.handleError)
    );
  }

  // Get all caretakers
  getAllCaretakers(): Observable<ServiceProvider[]> {
    return this.http.get(`${this.API_BASE_URL}/caretakers`).pipe(
      map((res: any) => res),
      catchError(this.handleError)
    );
  }

  // Get provider by id
  getServiceProviderById(providerId: string): Observable<ServiceProvider> {
    return this.http.get(`${this.API_BASE_URL}/${providerId}`).pipe(
      map((res: any) => res),
      catchError(this.handleError)
    );
  }

  // Get provider by email
  getServiceProvidersByEmail(email: string): Observable<ServiceProvider> {
    return this.http.get(`${this.API_BASE_URL}/email/${email}`).pipe(
      map((res: any) => res),
      catchError(this.handleError)
    );
  }

  // Get doctors by location
  getDoctorsByLocation(location: string): Observable<ServiceProvider[]> {
    return this.http
      .get(`${this.API_BASE_URL}/doctors/location/${location}`)
      .pipe(
        map((res: any) => res),
        catchError(this.handleError)
      );
  }

  // Get caretakers by location
  getCareTakersByLocation(location: string): Observable<ServiceProvider[]> {
    return this.http
      .get(`${this.API_BASE_URL}/caretakers/location/${location}`)
      .pipe(
        map((res: any) => res),
        catchError(this.handleError)
      );
  }

  // Add provider
  addServiceProvider(newProvider: ServiceProvider): Observable<any> {
    return this.http
      .post(`${this.API_BASE_URL}`, newProvider, {
        headers: this.getAuthHeader(),
      })
      .pipe(
        map((res: any) => res),
        catchError(this.handleError)
      );
  }

  // Update provider
  updateServiceProvider(providerId: string, updatedProvider: ServiceProvider) {
    return this.http
      .patch(`${this.API_BASE_URL}/${providerId}`, updatedProvider, {
        headers: this.getAuthHeader(),
      })
      .pipe(
        map((res: any) => res),
        catchError(this.handleError)
      );
  }

  // Delete provider
  deleteServiceProvider(providerId: string): Observable<any> {
    return this.http
      .delete(`${this.API_BASE_URL}/${providerId}`, {
        headers: this.getAuthHeader(),
      })
      .pipe(
        map((res: any) => res),
        catchError(this.handleError)
      );
  }

  private handleError(error: HttpErrorResponse) {
    if (error.error instanceof ErrorEvent) {
      // A client-side or network error occurred. Handle it accordingly.
      console.error('An error occurred:', error.error.message);
    } else {
      // The backend returned an unsuccessful response code.
      // The response body may contain clues as to what went wrong.
      console.error(
        `Backend returned code ${error.status}, body was: ${error.error}`
      );
    }
    // Return an observable with a user-facing error message.
    return throwError(
      () => new Error('Something bad happened; Please try again later.')
    );
  }
}
