import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { RouterService } from 'src/app/healthroute/Service/router.service';
import { PatientserviceService } from '../patientservice.service';
import { Patient } from '../Models/patient.model';
import { HttpHeaders } from '@angular/common/http';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent {
  isNotificationsOpen: boolean = false;
  isLoggedIn: boolean = false;
  notificationsCount: number = 0;
  tokendata:string | null = "";
  emailId:string | null = "";
  currentPatient:Patient =new Patient();

  constructor(private router:RouterService,private snackbar:MatSnackBar, private service:PatientserviceService) {
    console.log("isLoggedIn");
    console.log(this.isLoggedIn);
    console.log("---------");
  }

  ngOnInit(): void {
    //this.tokendata = sessionStorage.getItem('token');
    this.emailId = "albert@gmail.com";
    this.tokendata = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJoZWltZXJzY2htaWR0QGdtYWlsLmNvbSIsImF1ZCI6IkFQSXMiLCJSb2xlIjoiQWRtaW4iLCJ1bmlxdWVfbmFtZSI6ImhlaW1lcnNjaG1pZHRAZ21haWwuY29tIiwiaXNzIjoiU3F1YWQgQSIsIm5iZiI6MTY4MDAzNzkxNCwiaWF0IjoxNjgwMDM3OTE0LCJleHAiOjE2ODA2NDI3MTR9.mMV-HXd7nk_o-g2OwjGtNUTbhmkbNiMHwZ9-5xkpyT0";
    if(this.tokendata != "" && this.tokendata != null) {
      this.isLoggedIn = true;
      this.getCurrentPatient(this.emailId);
    }
    if(sessionStorage.getItem('bearerToken')!=null){
      this.isLoggedIn=true;
    }
    else{
      this.isLoggedIn=false;
    }
    
  }

  getCurrentPatient(emailId:string){
    let headers = new HttpHeaders().set("Authorization", `Bearer ${this.tokendata}`);
    this.service.getPatientByEmail(headers,emailId).subscribe((res:any) => {
      this.currentPatient = res;
    })
  }


  updateNoOfNotifications(noOfNotifications: number) {
    this.notificationsCount = noOfNotifications;
  }

  openNotifications() {
    this.isNotificationsOpen = true;
  }

  closeNotifications() {
    this.isNotificationsOpen = false;
  }
  logout() {
    //clear sessionStorage
    if(this.isLoggedIn){
      sessionStorage.clear();
      this.isLoggedIn=false;
      this.router.openLogin();
    this.snackbar.open("Logged Out","X",{
      duration:2000,
      verticalPosition: 'top',
      horizontalPosition: 'center',
    });
  }

  }

}
