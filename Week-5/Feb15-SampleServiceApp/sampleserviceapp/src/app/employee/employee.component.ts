import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { EmpserveService } from '../empserve.service';
import { Employee } from '../model/emp';

@Component({
  selector: 'app-employee',
  templateUrl: './employee.component.html',
  styleUrls: ['./employee.component.css']
})
export class EmployeeComponent  implements OnInit {

  form:any = {};
  employees: Array<Employee> = [];

  constructor(private empserve : EmpserveService) {
  }

  ngOnInit(): void {
    this.loadEmployee();
  }

  onClick() {
    console.log(this.form)
    this.empserve.addEmployee(this.form).subscribe(
      (res : any) => {
        console.log(res);
        this.employees.push(res);
      },
      (err : any) => {
        console.log(err);
      }
    )
  }

  delete(paramid : any) {
    this.empserve.deleteEmployee(paramid).subscribe(
      (res : any) => {
        console.log(res);
        let index = this.employees.findIndex( emp => emp.id === paramid);
        this.employees.splice(index, 1);
      },
      (err : any) => {
        console.log(err);
      }
    )
  }

  loadEmployee() {
    this.empserve.getEmployees().subscribe(
      (res : any) => {
        this.employees=res;
        console.log(res);
      },
      (err : any) => {
        console.log(err);
      }
    )
  }
}
