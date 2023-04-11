import { Component } from '@angular/core';
import { PatientserviceService } from '../patientservice.service';
import { HttpHeaders } from '@angular/common/http';
import { Prescription } from '../Models/prescription.model';
import { Patient } from '../Models/patient.model';

@Component({
  selector: 'app-prescription',
  templateUrl: './prescription.component.html',
  styleUrls: ['./prescription.component.css']
})
export class PrescriptionComponent {
  dataSource:Prescription[] = []
  displayedColumns = ['dosage', 'refills', 'doctorID', 'expiration'];
  emailId : any;
  tokendata:any;
  headers:any;
  currentPatient:Patient = new Patient();

  constructor(private patientService:PatientserviceService) {
  }

  ngOnInit():void {
    // this.emailId = "albert@gmail.com";
    // this.tokendata = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJoZWltZXJzY2htaWR0QGdtYWlsLmNvbSIsImF1ZCI6IkFQSXMiLCJSb2xlIjoiQWRtaW4iLCJ1bmlxdWVfbmFtZSI6ImhlaW1lcnNjaG1pZHRAZ21haWwuY29tIiwiaXNzIjoiU3F1YWQgQSIsIm5iZiI6MTY4MDAzNzkxNCwiaWF0IjoxNjgwMDM3OTE0LCJleHAiOjE2ODA2NDI3MTR9.mMV-HXd7nk_o-g2OwjGtNUTbhmkbNiMHwZ9-5xkpyT0";
    this.emailId = sessionStorage.getItem("emailId");
    this.tokendata = sessionStorage.getItem("bearerToken");
    this.headers = new HttpHeaders().set("Authorization", `Bearer ${this.tokendata}`);
    this.getCurrentPatient(this.emailId);
    
  }

  getCurrentPatient(emailId:string){
    this.patientService.getPatientByEmail(this.headers,emailId).subscribe((res:any) => {
      console.log(res.prescriptions);
      this.currentPatient = res;
      this.dataSource = res.prescriptions;
    })
    
  }
  addprescription(){
    let newPrescription = new Prescription();
    newPrescription.dosage = "Ibuprofen 3mg / day";
    newPrescription.refills = 4;
    newPrescription.doctorID = "43rfwdsfdgesgaf";
    console.log(this.currentPatient.id);
    this.patientService.addPrescription(this.headers,"642c456d5317631859f96305", newPrescription).subscribe((res:any) => {
      console.log(res);
    });
  }
}
