import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PatientdashboardComponent } from './patientdashboard/patientdashboard.component';
import { PatientprofileComponent } from './patientprofile/patientprofile.component';
import { PrescriptionComponent } from './prescription/prescription.component';

const routes: Routes = [
  { path: 'patientprofile', component: PatientprofileComponent },
  { path: 'prescription', component:PrescriptionComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PatientRoutingModule { }
