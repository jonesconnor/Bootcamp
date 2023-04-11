import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { map, Observable } from 'rxjs';
import { Patient } from './Models/patient.model';
import { Prescription } from './Models/prescription.model';

@Injectable({
  providedIn: 'root'
})
export class PatientserviceService {

  constructor(private http:HttpClient, private router:Router) { }
  
  getAllPatient(headers:any):Observable<any> {
    return this.http.get('http://localhost:9070/api/Patients', {headers}).pipe(map((res:any) => res));
  }

  getPatientByEmail(headers:any, email:string):Observable<any>{
    return this.http.get('http://localhost:9070/api/Patients/email/' + email, {headers}).pipe(map((res:any) => res));
  }
  getPatientById(headers:any, id:string):Observable<any>{
    return this.http.get('http://localhost:9070/api/Patients/' + id, {headers}).pipe(map((res:any) => res));
  }
  addPatient(newPatient:Patient):Observable<any>{
    return this.http.post('http://localhost:9070/api/Patients/', newPatient).pipe(map((res:any) => res));
  }

  updatePatient(headers:any, id:string, editedPatient:any):Observable<any> {
    return this.http.put('http://localhost:9070/api/Patients/'+id, editedPatient, {headers}).pipe(map((res:any) => res));
  }

  deletePatient(headers:any, id:string):Observable<any> {
    return this.http.delete('http://localhost:9070/api/Patients/' + id, {headers}).pipe(map((res:any) => res));
  }

  getAllPrescriptions(headers:any, id:string):Observable<any> {
    return this.http.get('http://localhost:9070/api/Patients/'+id+'/prescriptions', {headers}).pipe(map((res:any) => res));
  }

  addPrescription(headers:any, id:string, prescription:any):Observable<any>{
    return this.http.put('http://localhost:9070/api/Patients/'+id+'/prescriptions', prescription, {headers}).pipe(map((res:any) => res));
  }

  updatePrescription(headers:any, id:string, prescriptionId:string, prescription:any):Observable<any>{
    return this.http.put('http://localhost:9070/api/Patients/'+id+'/prescriptions/'+prescriptionId, prescription, {headers}).pipe(map((res:any) => res));
  }

  deletePrescription(headers:any, id:string, prescriptionId:string):Observable<any>
  {
    return this.http.put('http://localhost:9070/api/Patients/'+id+'/prescriptions/delete/'+prescriptionId, prescriptionId, {headers}).pipe(map((res:any) => res));
  }

  getNotification(headers:any, id:string):Observable<any> {
    return this.http.get('http://localhost:9070/api/Patients/'+id+'/notes', {headers}).pipe(map((res:any) => res));
  }

  addNotification(headers:any, id:string, note:any):Observable<any>{
    return this.http.put('http://localhost:9070/api/Patients/'+id+'/notes/'+note, note, {headers}).pipe(map((res:any) => res));
  }

  deleteNotification(headers:any, id:string, note:any):Observable<any> {
    return this.http.put('http://localhost:9070/api/Patients/'+id+'/notes/delete/' + note, note, {headers}).pipe(map((res:any) => res));
  }

  






}