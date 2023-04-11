import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AddcustomerComponent } from './addcustomer/addcustomer.component';
import { ViewcustomerComponent } from './viewcustomer/viewcustomer.component';
import { FormsModule } from '@angular/forms';



@NgModule({
  declarations: [
    AddcustomerComponent,
    ViewcustomerComponent
  ],
  imports: [
    CommonModule,
    FormsModule
  ],
  exports:[
    AddcustomerComponent,
    ViewcustomerComponent,
    FormsModule
    
  ]
})
export class CustomerModule { }
