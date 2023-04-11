import { Component, EventEmitter, OnInit, Output } from '@angular/core';

import { MatSnackBar } from '@angular/material/snack-bar';
import { RouterService } from 'src/app/healthroute/Service/router.service';
import { ProvidersService } from '../services/providers.service';
import { PatientserviceService } from 'src/app/patient/patientservice.service';
import { HttpHeaders } from '@angular/common/http';
import { lastValueFrom } from 'rxjs';

@Component({
  selector: 'app-notification',
  templateUrl: './notification.component.html',
  styleUrls: ['./notification.component.css'],
})
export class NotificationComponent implements OnInit {
  @Output() notificationDeleted = new EventEmitter<number>();

  notifications: string[] = [];
  providerId: string | null = '';
  patientId =""

  constructor(
    private route: RouterService,
    private api: ProvidersService,
    private _snackBar: MatSnackBar,
    private patientService: PatientserviceService
  ) {}

  ngOnInit(): void {
    if (sessionStorage.getItem('emailId') === null) {
      return;
    }
    if (sessionStorage.getItem('Role') == 'ServiceProvider') {
      this.getNotificationsForProvider();
    } else if (sessionStorage.getItem('Role') == 'Patient') {
      this.getNotificationsForPatient();
    }
  }

  // Open a snackbar with the given message, action and duration
  openSnackBar(message: string, action: string, duration: number = 0) {
    if (duration) {
      return this._snackBar.open(message, action, { duration: duration });
    }
    return this._snackBar.open(message, action);
  }

  // Removes the notification from the list
  removeNotification(message: string) {
    if (sessionStorage.getItem('Role') == 'ServiceProvider') {
      this.api
      .deleteNotificationForProvider(this.providerId!, message)
      .subscribe({
        next: (_res: any) => {
          this.getNotificationsForProvider();
          this.notificationDeleted.emit(this.notifications.length);
        },
        error: (error: any) => {
          this.openSnackBar(
            'Unable to delete notification. Try again later.',
            'Dismiss'
          );
          console.log(error);
        },
      });

    } else if (sessionStorage.getItem('Role') == 'Patient') {
      let tokendata = sessionStorage.getItem('bearerToken');
      let headers = new HttpHeaders().set('Authorization', `Bearer ${tokendata}`);
      this.patientService.deleteNotification(headers, this.patientId!,message)
      .subscribe({
        next: (_res: any) => {
          this.getNotificationsForPatient()
          this.notificationDeleted.emit(this.notifications.length);
        },
        error: (error: any) => {
          this.openSnackBar(
            'Unable to delete notification. Try again later.',
            'Dismiss'
          );
          console.log(error);
        },
      });
    }
    
  }

  // Get all notifications for the logged in service provider
  async getNotificationsForProvider() {
    const providerEmail = sessionStorage.getItem('emailId');
    const response = this.api.getServiceProvidersByEmail(providerEmail!)
    let prov = await lastValueFrom(response)
    const providerId = prov.id
    this.providerId = prov.id!

    if (providerId === null) {
      this.route.openLogin();
      return;
    }

    this.api.getNotificationsForProvider(this.providerId!).subscribe((res) => {
      console.log(res);
      this.notifications = res;
      this.notificationDeleted.emit(this.notifications.length);
    });
  }

  async getNotificationsForPatient() {
    
    const patientEmail = sessionStorage.getItem('emailId');
    let tokendata = sessionStorage.getItem('bearerToken');
    let headers = new HttpHeaders().set('Authorization', `Bearer ${tokendata}`);
    const response = this.patientService.getPatientByEmail(headers,patientEmail!)
    let patient = await lastValueFrom(response)
    this.patientId = patient.id
    
    if (!this.patientId) {
      this.route.openLogin();
      return;
    }
    
    this.patientService
      .getNotification(headers,this.patientId!)
      .subscribe((res) => {
        this.notifications = res;
        this.notificationDeleted.emit(this.notifications.length);
      });
  }
}
