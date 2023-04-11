import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs/internal/BehaviorSubject';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { MedicalRecord } from './model/MedicalRecord';
import { Observable } from 'rxjs/internal/Observable';
import { environment } from 'environment';
import { tap } from 'rxjs/internal/operators/tap';

@Injectable({
  providedIn: 'root'
})
export class MrService {
  private medicalRecordListSubject = new BehaviorSubject<MedicalRecord[]>([]);
  public medicalRecordList = this.medicalRecordListSubject.asObservable();

  constructor(private httpclient: HttpClient) { }

  addRecord(record: MedicalRecord): Observable<MedicalRecord> {
    const token = sessionStorage.getItem('bearerToken');
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`
    });
    return this.httpclient.post<MedicalRecord>(environment.addRecordAPI, record, { headers })
    .pipe(
      tap((newRecord: MedicalRecord) => {
        const updatedList = [...this.medicalRecordListSubject.value, newRecord];
        this.medicalRecordListSubject.next(updatedList);
      })
    );
  }

  // should this tie into the behavior subject if it's just returning a single record?
  getRecordById(recordid: number): Observable<MedicalRecord> {
    const token = sessionStorage.getItem('bearerToken');
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`
    });
    return this.httpclient.get<MedicalRecord>(environment.getRecordAPI + `/${recordid}`, { headers })
  }

  getRecordsByPatient(patientid: string): Observable<Array<MedicalRecord>> {
    const token = sessionStorage.getItem('bearerToken');
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`
    });
    return this.httpclient.get<Array<MedicalRecord>>(environment.getRecordAPI + `/patient/${patientid}`, { headers })
      .pipe(
        tap((records: MedicalRecord[]) => {
          this.medicalRecordListSubject.next(records);
        })
      );
  }

  getRecordsByPatiendidAndDate(patientid: string, date: string): Observable<Array<MedicalRecord>> {
    const token = sessionStorage.getItem('bearerToken');
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`
    });
    return this.httpclient.get<Array<MedicalRecord>>(environment.getRecordAPI + `/patientdate/${patientid}/${date}`, { headers })
      .pipe(
        tap((records: MedicalRecord[]) => {
          this.medicalRecordListSubject.next(records);
        })
      );
  }

  getRecordsByDoctoridAndPatientid(doctorid: string, patientid: string): Observable<Array<MedicalRecord>> {
    const token = sessionStorage.getItem('bearerToken');
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`
    });
    return this.httpclient.get<Array<MedicalRecord>>(environment.getRecordAPI + `/doctorpatient/${doctorid}/${patientid}`, { headers })
      .pipe(
        tap((records: MedicalRecord[]) => {
          this.medicalRecordListSubject.next(records);
        })
      );
  }

  getRecordsByDoctoridAndDate(doctorid: string, date: string): Observable<Array<MedicalRecord>> {
    const token = sessionStorage.getItem('bearerToken');
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`
    });
    return this.httpclient.get<Array<MedicalRecord>>(environment.getRecordAPI + `/doctordate/${doctorid}/${date}`, { headers })
      .pipe(
        tap((records: MedicalRecord[]) => {
          this.medicalRecordListSubject.next(records);
        })
      );
  }

}
