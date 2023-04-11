import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';

import { EmpserveService } from '../empserve.service';
import { Employee } from '../model/emp';

@Component({
  selector: 'app-employee',
  templateUrl: './employee.component.html',
  styleUrls: ['./employee.component.css']
})
export class EmployeeComponent implements OnInit {

//form:any =new Employee()
errmsg:string=""
form:any={}
searchtext:string=""

empobj : Employee=new Employee()

employees : Array<Employee>=[]

   constructor(private empserve : EmpserveService)
   {
     
   }
  ngOnInit(): void {
    this.loadEmployee();
  }

  onClick()
  {
 console.log(this.form)

 this.empserve.addEmployee(this.form).subscribe(
   (res)=>
   {
       console.log( res)
       this.employees.push(res)
      //  this.ngOnInit()
   },
   (err)=>
   {
    this.errmsg=err
    
   }
 

 )


  }

  delete(paramid : any)
  {
    this.empserve.deleteEmployee(paramid).subscribe(
      (res)=>
      {
         let ind=this.employees.findIndex( emp=> emp.id===paramid);
         this.employees.splice(ind,1)
      },
      (err)=>
      {
        this.errmsg=err
      }
    )
  }



   loadEmployee()
   {
    this.empserve.getEmployees().subscribe(
      (res:any)=>
      {
        this.employees=res
        console.log(res)
      },

      (err:any)=>
      {
        this.errmsg=err
      }
    )
   }
}
