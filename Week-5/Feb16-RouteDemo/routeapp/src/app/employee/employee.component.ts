import { RouterserveService } from './../routerserve.service';
import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { EmpserveService } from '../empserve.service';
import { Employee } from '../model/emp';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-employee',
  templateUrl: './employee.component.html',
  styleUrls: ['./employee.component.css']
})
export class EmployeeComponent  implements OnInit {

  form:any = {};
  employees: Array<Employee> = [];
  filteredEmployees: Array<Employee> = [];
  prole:string | null="";

  constructor(private empserve : EmpserveService, private activeroute: ActivatedRoute, private routeobj: RouterserveService) {
    this.prole = this.activeroute.snapshot.paramMap.get('paramrole')
  }

  ngOnInit(): void {
    this.loadEmployee();
    this.filteredEmployees = this.employees.filter(emp=>emp.role === this.prole)
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

  filter() {
    this.filteredEmployees = this.employees.filter(emp => emp.role === this.prole);
  }
}
