import { Component } from '@angular/core';
import { EmpserveService } from '../services/empserve.service';
import { Employee } from '../model/emp';

@Component({
  selector: 'app-empview',
  templateUrl: './empview.component.html',
  styleUrls: ['./empview.component.css'],
   
})
export class EmpviewComponent {

  employees : Array<Employee>=[]
  errmsg:string=""
count:number=0;
  constructor(private empserve : EmpserveService)
  {
   
  }

  display(data:any)
  {
this.count=data

  }
  
  ngOnInit(): void {
    this.loadEmployee();
  }
  loadEmployee()
  {
   this.empserve.getEmployees().subscribe({
     next : (res:any)=>
     {
       this.employees=res
     },
 
     error:(err:any)=>
     {
       this.errmsg=err
     }

    }
   )
    }
 
}
