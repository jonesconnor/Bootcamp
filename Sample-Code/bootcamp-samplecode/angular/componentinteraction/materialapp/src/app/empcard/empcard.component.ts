import { Component, EventEmitter, Input, Output } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Employee } from '../model/emp';
import { EmpserveService } from '../services/empserve.service';
import { UpdateempdialogComponent } from '../updateempdialog/updateempdialog.component';

@Component({
  selector: 'app-empcard',
  templateUrl: './empcard.component.html',
  styleUrls: ['./empcard.component.css']
})
export class EmpcardComponent {

  @Input()
  emp:Employee=new Employee()

  usercount : number=0
  @Output() changeData=new EventEmitter();
  
  constructor(private empservobj: EmpserveService,private matdialogobj:MatDialog)
{

}

sendCount()
{
  this.usercount+=1
  this.changeData.emit(this.usercount)

}
  delete()
  {
    this.empservobj.deleteEmployee(this.emp.id).subscribe
    (
      (res)=>
      console.log("deleted")
    )
  }

  update()
  {
  this.matdialogobj.open(UpdateempdialogComponent,{
    width:'30%',
    height:'40%',
    data:{emptoupd : this.emp}
  }).afterClosed().subscribe (
    res=>
      {
        if (res==="Update")
        console.log("Employee record updated")
      }
  )
  }
}
