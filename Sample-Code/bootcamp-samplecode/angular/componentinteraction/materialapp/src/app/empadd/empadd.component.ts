import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import {EmpserveService} from '../services/empserve.service'

@Component({
  selector: 'app-empadd',
  templateUrl: './empadd.component.html',
  styleUrls: ['./empadd.component.css']
})
export class EmpaddComponent {

  empform: FormGroup;

  constructor(private empserobj:EmpserveService,private snackobj:MatSnackBar)
  {
    this.empform=new FormGroup({
      id:new FormControl(''),
      empName: new FormControl(''),
      role:new FormControl('',Validators.required)
    })
  }

  addemp()
  {
this.empserobj.addEmployee(this.empform.value).subscribe({
  next:(res:any)=>
  {
    this.snackobj.open("Employee Added","Employee",
    {
      duration:2000
    })
  },
  error:(err:any)=>
  {

  }
  
})
  
     this.clearform()
}
 
   //addemp

  clearform()
  {
    this.empform.reset({
      id:"",
      empName:"",
      role:""
    })
  }
}
