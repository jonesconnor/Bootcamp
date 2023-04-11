import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, tap } from 'rxjs';
import { environment } from 'src/environment';
import { Employee } from '../model/emp';

@Injectable({
  providedIn: 'root'
})
export class EmpserveService {

  token:any;
  employees : Array<Employee>=[]
  empsubject : BehaviorSubject<Array<Employee>>

  constructor(private httpclientobj : HttpClient) {
    this.token=sessionStorage.getItem("mytoken")
  this.empsubject=new BehaviorSubject<Array<Employee>>([])

   }

fetchEmployeeData()
{
  return this.httpclientobj.get<Array<Employee>>(environment.apilink,
    {
      headers:new HttpHeaders().set('Authorization','Bearer '+this.token)
    }).subscribe(
      (res:any)=>
      {
        this.employees=res;
        this.empsubject?.next(this.employees)
      }
    )
}




   getEmployees() : Observable<Array<Employee>>
   {
    return this.empsubject; // reflect the changes as and when it happens in the behaviour subject
   }

  addEmployee(emp : Employee) : Observable<Employee>
  {
    return this.httpclientobj.post<Employee>(environment.apilink,emp,{

      headers: new HttpHeaders().set('Authorization','Bearer '+this.token),
           
    }).pipe
    (
      tap( (res:any)=>
      {
this.employees.push(res)
this.empsubject?.next(this.employees)
return this.empsubject
      }
      )
    )
  }




 deleteEmployee(id : any) : Observable<any>
 {
return this.httpclientobj.delete(environment.apilink+ "/"+id,
  {

    headers: new HttpHeaders().set('Authorization','Bearer '+this.token),
         
  }
).pipe(
  tap( (res:any)=>
  {
    let ind=this.employees.findIndex( emp=> emp.id===id)
    this.employees.splice(ind,1)
    this.empsubject.next(this.employees)
    return this.empsubject
  }
  )
)
 }

  updateEmployee(emp:Employee) : Observable<any>
  {
    let headersobj=new HttpHeaders()
    headersobj.append('Content-Type','application/json');
    headersobj.append('Authorization',"Bearer "+ this.token)

    return this.httpclientobj.put(environment.apilink+ "/"+emp.id,emp,{
headers:headersobj
    })
    .pipe
    (
      tap( (res:any)=>
      {
      const existemp = this.employees.find(empobj=>empobj.id===emp.id)
       if(existemp)
       Object.assign(existemp,emp)

       this.empsubject.next(this.employees)
       return this.empsubject

      }




      )
    )
  }



  }


