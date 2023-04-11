import { Component } from '@angular/core';
import { EmpserveService } from '../services/empserve.service';
import { Employee } from '../model/emp';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent {
  errmsg:any 
  empobj : Employee=new Employee()
  // displayedColumns : string[] =['id','empName','role']
  // dataSource:any

  constructor(private empserobj : EmpserveService) {
    
    this.empserobj.fetchEmployeeData();
  }

   
   
}
