import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environment';
import { Employee } from './model/emp';

@Injectable({
  providedIn: 'root'
})
export class EmpserveService {

  token:any;

  constructor(private httpclientobj : HttpClient) {
    this.token=sessionStorage.getItem("mytoken")

   }

   getEmployees() : Observable<Array<Employee>>
   {
    return this.httpclientobj.get<Array<Employee>>(environment.apilink,
      {
        headers:new HttpHeaders().set('Authorization','Bearer '+this.token)
      });
   }

  addEmployee(emp : Employee) : Observable<Employee>
  {
    var headers_object = new HttpHeaders();
        headers_object.append('Content-Type', 'application/json');
        headers_object.append("Authorization", "Bearer " + this.token);

    return this.httpclientobj.post<Employee>(environment.apilink,emp,{

      headers: headers_object
           
    })
  }
 deleteEmployee(id : any) : Observable<any>
 {
return this.httpclientobj.delete(environment.apilink+ "/"+id,
  {

    headers: new HttpHeaders().set('Authorization','Bearer '+this.token)
 
         
  }
)
 }


  }


