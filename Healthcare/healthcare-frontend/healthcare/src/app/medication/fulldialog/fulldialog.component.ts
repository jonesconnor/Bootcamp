import { Component, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Result } from '../model/result';

@Component({
  selector: 'app-fulldialog',
  templateUrl: './fulldialog.component.html',
  styleUrls: ['./fulldialog.component.css']
})
export class FulldialogComponent {
  medication:Result
  id:String
  inactive:Array<String>
  purpose:Array<String>
  warnings:Array<String>
  indications:Array<String>
  active:Array<String>
  dosage:Array<String>

  constructor(private dialogref:MatDialogRef<FulldialogComponent>, @Inject(MAT_DIALOG_DATA) public data:any){
    
    this.medication=data.result
    this.id=this.medication.id!
    this.inactive=this.medication.inactive_ingredient!
    this.purpose=this.medication.purpose!
    this.warnings=this.medication.warnings!
    this.indications=this.medication.indications_and_usage!
    this.active=this.medication.active_ingredient!
    this.dosage=this.medication.dosage_and_administration!


  }

}
