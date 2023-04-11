import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Patient } from './model/patient';

@Injectable({
  providedIn: 'root'
})
export class PatientService {

  constructor(private httpcli: HttpClient ) { }

addPatient(pat : Patient) : Observable<Patient>
{
 return this.httpcli.post('http://localhost:9091/patient/addpatient',pat,{

  headers: new HttpHeaders({"Content-Type":"application/json"})
})
}

viewPatients() : Observable<Array<Patient>>
{

  return this.httpcli.get<Array<Patient>>('http://localhost:9091/patient/viewall');
}

}
