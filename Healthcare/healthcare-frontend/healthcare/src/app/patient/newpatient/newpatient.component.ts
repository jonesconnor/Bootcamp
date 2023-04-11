import { HttpHeaders } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { Patient } from '../Models/patient.model';

@Component({
  selector: 'app-newpatient',
  templateUrl: './newpatient.component.html',
  styleUrls: ['./newpatient.component.css']
})
export class NewpatientComponent {
  formvalue! : FormGroup;
  currentPatient : Patient = new Patient();
  currentId : any;
  emailId : any;
  tokendata:any;
  patienList:Patient[] = [];



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


    // this.patientService.addPatient(newPatient).subscribe((res:any) => {
    //   console.log("!!");
    // });
    
  }


}
