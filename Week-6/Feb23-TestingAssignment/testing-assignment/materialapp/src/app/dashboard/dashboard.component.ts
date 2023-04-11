import { Component } from '@angular/core';
import { EmpserveService } from '../empserve.service';
import { Employee } from '../model/emp';
import { RouteserveService } from '../routeserve.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent {
  errmsg:any 
  empobj : Employee=new Employee()
  displayedColumns : string[] =['id','empName','role']
  dataSource:any

  
  employees : Array<Employee>=[]
    constructor(private empserve : EmpserveService,private routeobj:RouteserveService)
    {
      
    }
   ngOnInit(): void {
     this.loadEmployee();
   }
   loadEmployee()
   {
    this.empserve.getEmployees().subscribe(
      (res:any)=>
      {
        this.employees=res
        this.dataSource=this.employees
        console.log(res)
      },
  
      (err:any)=>
      {
        this.errmsg=err
      }
    )
   }
   logout()
   {
this.routeobj.openLogin();
sessionStorage.clear()
   }
}
