import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { CustomerModule } from './customer/customer.module';
import { EmployeeComponent } from './employee/employee.component';
import { Empsample2Component } from './empsample2/empsample2.component';

@NgModule({
  declarations: [
    AppComponent,
    EmployeeComponent,
    Empsample2Component
  ],
  imports: [
    BrowserModule,
    FormsModule,
    CustomerModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
