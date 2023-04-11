import { Component } from '@angular/core';
import { Employee } from 'src/model/employee';

@Component({
  selector: 'app-empsample2',
  templateUrl: './empsample2.component.html',
  styleUrls: ['./empsample2.component.css']
})
export class Empsample2Component {
  name : string ="dan";
  empobj : Employee = new Employee()

  today :string = "2023/02/28"

  employees : Employee[] = [];

display()
{
  alert("welcome " + this.name)
}

register(eid:any,ename :string)
{
  // console.log(this.empobj.empId + " name  " + this.empobj.empName )
   if(ename!=='')
   {
 const empdata : Employee ={empId:eid,empName:ename,isRetired:false}

   this.employees.push(empdata);
 (document.getElementById("empid") as HTMLInputElement).value='';
 (document.getElementById("empname") as HTMLInputElement).value=''; 
   
   }
// console.log(this.employees)
}

deleteEmp(eid:any)
{
  let ind=this.employees.findIndex(emp=>emp.empId===eid);
  this.employees.splice(ind,1);
  

}

changeIt(emp:Employee)
{
  if(emp.isRetired)
  emp.isRetired=false
  else
  emp.isRetired=true
}



}