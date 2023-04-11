import { Component } from '@angular/core';
import { Employee } from 'src/model/employee';

@Component({
  selector: 'app-employee',
  templateUrl: './employee.component.html',
  styleUrls: ['./employee.component.css']
})
export class EmployeeComponent {

  name : string ="dan";
  empobj : Employee = new Employee()

  employees : Employee[] = [];

display()
{
  alert("welcome " + this.name)
}

register()
{
  // console.log(this.empobj.empId + " name  " + this.empobj.empName )
   if(this.empobj.empName!=='')
   {
   this.employees.push(this.empobj);
    this.empobj=new Employee();
   }
 console.log(this.employees)
}

deleteEmp(eid:any)
{
  let ind=this.employees.findIndex(emp=>emp.empId===eid);
  this.employees.splice(ind,1);
  

}
}
