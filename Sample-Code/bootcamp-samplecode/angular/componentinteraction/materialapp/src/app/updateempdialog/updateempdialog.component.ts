import { Component, Inject, OnDestroy } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Employee } from '../model/emp';
import { EmpserveService } from '../services/empserve.service';
import { RouteserveService } from '../services/routeserve.service';

@Component({
  selector: 'app-updateempdialog',
  templateUrl: './updateempdialog.component.html',
  styleUrls: ['./updateempdialog.component.css']
})
export class UpdateempdialogComponent implements OnDestroy {
cname :string =""
empobj : Employee=new Employee()

constructor(private dialogref:MatDialogRef<UpdateempdialogComponent>,
  @Inject(MAT_DIALOG_DATA) public data:any,private empserveobj : EmpserveService,
  private routeservobj:RouteserveService
  )
{

   this.empobj=data.emptoupd
}
  ngOnDestroy(): void {
  this.routeservobj.openDashboard()
  }


update()
{
  this.empserveobj.updateEmployee(this.empobj).subscribe(
    (res)=>
    {
      this.dialogref.close("Update")
    }

  )
}
}
