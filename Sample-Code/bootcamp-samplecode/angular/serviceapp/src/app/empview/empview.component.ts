import { Component, OnInit } from '@angular/core';
import { EmpserveService } from '../empserve.service';
import { Employee } from '../model/emp';

@Component({
  selector: 'app-empview',
  templateUrl: './empview.component.html',
  styleUrls: ['./empview.component.css']
})
export class EmpviewComponent implements OnInit{

  errmsg:any 
empobj : Employee=new Employee()

employees : Array<Employee>=[]
  constructor(private empserve : EmpserveService)
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
      console.log(res)
    },

    (err:any)=>
    {
      this.errmsg=err
    }
  )
 }
}
