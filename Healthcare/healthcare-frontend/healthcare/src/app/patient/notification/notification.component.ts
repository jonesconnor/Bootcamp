import { Component, EventEmitter, Output } from '@angular/core';
import { PatientserviceService } from '../patientservice.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Patient } from '../Models/patient.model';
import { HttpHeaders } from '@angular/common/http';

@Component({
  selector: 'app-notification',
  templateUrl: './notification.component.html',
  styleUrls: ['./notification.component.css']
})
export class NotificationComponent {
  @Output() notificationDeleted = new EventEmitter<number>();

  emailId:string | null= "" ;
  tokendata:string | null= "" ;
  notes:string[] = [];
  currentPatient :Patient = new Patient();

  constructor(private service:PatientserviceService, private snackbar:MatSnackBar) {

  }

  ngOnInit(): void {
    //this.emailId = localStorage.getItem('userId');
    this.emailId = "albert@gmail.com";
    this.tokendata = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJoZWltZXJzY2htaWR0QGdtYWlsLmNvbSIsImF1ZCI6IkFQSXMiLCJSb2xlIjoiQWRtaW4iLCJ1bmlxdWVfbmFtZSI6ImhlaW1lcnNjaG1pZHRAZ21haWwuY29tIiwiaXNzIjoiU3F1YWQgQSIsIm5iZiI6MTY4MDAzNzkxNCwiaWF0IjoxNjgwMDM3OTE0LCJleHAiOjE2ODA2NDI3MTR9.mMV-HXd7nk_o-g2OwjGtNUTbhmkbNiMHwZ9-5xkpyT0";
    this.getCurrentPatient(this.emailId);
    if (this.emailId === null) {
      console.log("!!");// Add code to redirect to login
      return;
    }
    
    
  }
  
  getCurrentPatient(emailId:string){
    let headers = new HttpHeaders().set("Authorization", `Bearer ${this.tokendata}`);
    this.service.getPatientByEmail(headers,emailId).subscribe((res:any) => {
      this.currentPatient = res;
      console.log(this.currentPatient);
      this.notes = this.currentPatient.notifications;
    })
  }

  openSnackBar(message: string, action: string, duration: number = 0) {
    if (duration) {
      return this.snackbar.open(message, action, { duration: duration });
    }
    return this.snackbar.open(message, action);
  }

  removeNotification(message: string) {
    let headers = new HttpHeaders().set("Authorization", `Bearer ${this.tokendata}`);
    // this.service
    //   .deleteNotification(headers, this.currentPatient.id, message)
    //   .subscribe({
    //     next: (_res: any) => {
    //       this.getAllNotes();
    //       this.notificationDeleted.emit(this.notes.length);
    //     },
    //     error: (error: any) => {
    //       this.openSnackBar(
    //         'Cannot delete the notification',
    //         'Dismiss'
    //       );
    //       console.log(error);
    //     },
    //   });
  }



  getAllNotes() {
  
    if (!this.emailId) {
      // Add code to redirect to login
      return;
    }
    let headers = new HttpHeaders().set("Authorization", `Bearer ${this.tokendata}`);
    this.service.getNotification(headers,this.currentPatient.id).subscribe((res) => {
      console.log(res);
      this.notes = res;
      this.notificationDeleted.emit(this.notes.length);
    });
  }



}
