import { Injectable } from '@angular/core';
import { HttpClient} from '@angular/common/http';
import { Medication } from './model/medication';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MedicationService {

  constructor(private httpobj: HttpClient) { }

  viewMedications(): Observable<Medication>{
    return this.httpobj.get<Medication>("http://localhost:9092/viewall");
  }
}
