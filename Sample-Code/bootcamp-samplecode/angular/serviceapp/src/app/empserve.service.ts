import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environment';
import { Employee } from './model/emp';

@Injectable({
  providedIn: 'root'
})
export class EmpserveService {

  constructor(private httpclientobj : HttpClient) {


   }

   getEmployees() : Observable<Array<Employee>>
   {
    return this.httpclientobj.get<Array<Employee>>(environment.apilink);
   }

  addEmployee(emp : Employee) : Observable<Employee>
  {
    return this.httpclientobj.post<Employee>(environment.apilink,emp,{

      headers: new HttpHeaders({"Content-Type":"application/json"})
    })
  }
 deleteEmployee(id : any) : Observable<any>
 {
return this.httpclientobj.delete(environment.apilink+ "/"+id)
 }


  }


