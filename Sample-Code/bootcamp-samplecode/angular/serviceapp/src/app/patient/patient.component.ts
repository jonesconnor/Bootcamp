import { Component } from '@angular/core';
import { EmpserveService } from '../empserve.service';
import { Patient } from '../model/patient';
import { PatientService } from '../patient.service';

@Component({
  selector: 'app-patient',
  templateUrl: './patient.component.html',
  styleUrls: ['./patient.component.css']
})
export class PatientComponent {

  pat :Patient=new Patient();
patients : Array<Patient> =[];

constructor(private eserve:PatientService)
{
this.getallPatient();
}
addPatient()
{
this.eserve.addPatient(this.pat).subscribe(
  res=>
  {
  console.log("Added")
console.log(res)
  },
  err=>
  {
    console.log(err.error);
    
  }
)
}

getallPatient()
{
this.eserve.viewPatients().subscribe(
  res=>
  {
    this.patients=res;
  }
)

}



}
