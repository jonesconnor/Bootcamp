import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { UserComponent } from './user/user.component';
import { AdminComponent } from './admin/admin.component';
import { ManagerComponent } from './manager/manager.component';
import { CommonlibService } from './commonlib.service';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { EmployeeComponent } from './employee/employee.component';
import { EmpviewComponent } from './empview/empview.component';
import { MypipePipe } from './mypipe.pipe';
import { PatientComponent } from './patient/patient.component';


@NgModule({
  declarations: [
    AppComponent,
    UserComponent,
    AdminComponent,
    ManagerComponent,
    EmployeeComponent,
    EmpviewComponent,
    MypipePipe,
    PatientComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule
 
 

  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
