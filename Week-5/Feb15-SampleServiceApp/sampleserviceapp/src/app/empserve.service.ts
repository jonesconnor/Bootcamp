import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Employee } from './model/emp';

@Injectable({
  providedIn: 'root'
})
export class EmpserveService {

  constructor(private httpclientobj : HttpClient) {

  }

  getEmployees() : Observable<Array<Employee>> {
    return this.httpclientobj.get<Array<Employee>>('http://localhost:3000/employee');
  }

  addEmployee(emp : Employee) : Observable<Employee> {
    return this.httpclientobj.post('http://localhost:3000/employee', emp, {
      headers: new HttpHeaders({"Content-Type":"application/json"})
    });
  }

  deleteEmployee(id : any) : Observable<any> {
    return this.httpclientobj.delete(`http://localhost:3000/employee/${id}`);
  }
}
