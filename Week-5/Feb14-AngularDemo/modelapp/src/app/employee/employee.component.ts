import { Component } from '@angular/core';
import { Employee } from '../model/employee';

@Component({
  selector: 'app-employee',
  templateUrl: './employee.component.html',
  styleUrls: ['./employee.component.css']
})
export class EmployeeComponent {
  name:string = "Connor";

  empobj:Employee = new Employee()

  employees:Employee[] = [];

  display() {
    alert(`Welcome ${this.name}`);
  }

  register() {
    // alert(`ID: ${this.empobj.empId} Name: ${this.empobj.empName}`)
    if (this.empobj.empName !== '') {
      this.employees.push(this.empobj);
      this.empobj = new Employee();
    }
  }

  deleteEmp(eid:any) {
    let index = this.employees.findIndex(emp=>emp.empId===eid);
    this.employees.splice(index,1);
  }

  changeStatus(emp:Employee) {
    if (emp.isretired) {
      emp.isretired=false;
    } else {
      emp.isretired=true;
    }
  }

}
