import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Patient } from '../Models/patient.model';
import { PatientserviceService } from '../patientservice.service';
import { MatSnackBar } from '@angular/material/snack-bar';



@Component({
  selector: 'app-patientprofile',
  templateUrl: './patientprofile.component.html',
  styleUrls: ['./patientprofile.component.css']
})
export class PatientprofileComponent {
  formvalue! : FormGroup;
  currentPatient : Patient = new Patient();
  currentId : any;
  emailId : any;
  tokendata:any;
  patienList:Patient[] = [];

  constructor(private fb: FormBuilder, private patientService:PatientserviceService, private snackbar:MatSnackBar) {}

  ngOnInit():void {
    this.formvalue = this.fb.group({
      firstname:['',[Validators.required,Validators.maxLength(30),Validators.minLength(2)]],
      lastname:['',[Validators.required,Validators.maxLength(30),Validators.minLength(2)]],
      email:['',[Validators.required,Validators.email]],
      phone:['',[Validators.required,Validators.pattern("[0-9]{10}")]],
      phaemacyphone:[''],
      address:['',[Validators.required,Validators.maxLength(50),Validators.minLength(5)]],
      city:['',[Validators.required,Validators.maxLength(30),Validators.minLength(2)]],
      postcode:['',[Validators.required,Validators.pattern("[A-Za-z][0-9][A-Za-z][0-9][A-Za-z][0-9]"),]]
    })
    this.emailId = sessionStorage.getItem("emailId");
    this.tokendata = sessionStorage.getItem("bearerToken");
    //this.emailId = "albert@gmail.com";
    //this.tokendata = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJoZWltZXJzY2htaWR0QGdtYWlsLmNvbSIsImF1ZCI6IkFQSXMiLCJSb2xlIjoiQWRtaW4iLCJ1bmlxdWVfbmFtZSI6ImhlaW1lcnNjaG1pZHRAZ21haWwuY29tIiwiaXNzIjoiU3F1YWQgQSIsIm5iZiI6MTY4MDAzNzkxNCwiaWF0IjoxNjgwMDM3OTE0LCJleHAiOjE2ODA2NDI3MTR9.mMV-HXd7nk_o-g2OwjGtNUTbhmkbNiMHwZ9-5xkpyT0";
    this.getCurrentPatient(this.emailId);

  }
  
  getCurrentPatient(emailId:string){
    let headers = new HttpHeaders().set("Authorization", `Bearer ${this.tokendata}`);
    this.patientService.getPatientByEmail(headers,emailId).subscribe((res:any) => {
      this.loadCurrentPatient(res);
      
    })
    
  }
  
  loadCurrentPatient(data:Patient){
    this.currentPatient = data;
    this.formvalue.controls['firstname'].setValue(data.firstName);
    this.formvalue.controls['lastname'].setValue(data.lastName);
    this.formvalue.controls['email'].setValue(data.email);
    this.formvalue.controls['phone'].setValue(data.phoneNumber);
    this.formvalue.controls['phaemacyphone'].setValue(data.phaemacyPhoneNumber);
    this.formvalue.controls['address'].setValue(data.address);
    this.formvalue.controls['city'].setValue(data.municipality);
    this.formvalue.controls['postcode'].setValue(data.postalCode)
  }
  
  updatePatient() {
    this.currentPatient.firstName = this.formvalue.value.firstname;
    this.currentPatient.lastName = this.formvalue.value.lastname;
    this.currentPatient.email = this.formvalue.value.email;
    this.currentPatient.phoneNumber = this.formvalue.value.phone;
    this.currentPatient.phaemacyPhoneNumber = this.formvalue.value.phaemacyphone;
    this.currentPatient.address = this.formvalue.value.address;
    this.currentPatient.municipality = this.formvalue.value.city;
    this.currentPatient.postalCode = this.formvalue.value.postcode;

    let headers = new HttpHeaders().set("Authorization", `Bearer ${this.tokendata}`);
    this.patientService.updatePatient(headers, this.currentPatient.id, this.currentPatient).subscribe((res:any) => {
      this.snackbar.open("Profie Saved!","X",{
        duration:2000,
        verticalPosition: 'top',
        horizontalPosition: 'center',
      });
    });
    
  }

  addPatient() {
    let newPatient = new Patient();
    newPatient.firstName = this.formvalue.value.firstname;
    newPatient.lastName = this.formvalue.value.lastname;
    newPatient.email = this.formvalue.value.email;
    newPatient.phoneNumber = this.formvalue.value.phone;
    newPatient.phaemacyPhoneNumber = this.formvalue.value.phaemacyphone;
    newPatient.address = this.formvalue.value.address;
    newPatient.municipality = this.formvalue.value.city;
    newPatient.postalCode = this.formvalue.value.postcode;
    newPatient.phn = "234543";

    this.patientService.addPatient(newPatient).subscribe((res:any) => {
      this.snackbar.open("Profile Deleted. Redirecting to homepage...","X",{
        duration:3000,
        verticalPosition: 'top',
        horizontalPosition: 'center',
      });
      this.logout();
    });
    
  }

  addNotes(){
    let headers = new HttpHeaders().set("Authorization", `Bearer ${this.tokendata}`);
    this.patientService.addNotification(headers,this.currentPatient.id,"New Appointment is booked!").subscribe((res:any) => {
      
    });
  }
  deleteNotes(){
    let headers = new HttpHeaders().set("Authorization", `Bearer ${this.tokendata}`);
    this.patientService.deleteNotification(headers,this.currentPatient.id,"New Appointment is booked!").subscribe((res:any) => {
      
    });
  }

  getNotes(){
    let headers = new HttpHeaders().set("Authorization", `Bearer ${this.tokendata}`);
    this.patientService.getNotification(headers,this.currentPatient.id,).subscribe((res:any) => {
      console.log(res);
    });
  }

  logout(){
    sessionStorage.removeItem('bearerToken');
    sessionStorage.removeItem('Role');
    sessionStorage.removeItem('userId');
    sessionStorage.removeItem('name');
    sessionStorage.removeItem('emailId');
  }

  
}