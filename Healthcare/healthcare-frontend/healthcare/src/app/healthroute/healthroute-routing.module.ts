import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddProviderComponent } from 'src/service-providers/admin/add-provider/add-provider.component';
import { UpdateProviderComponent } from 'src/service-providers/admin/update-provider/update-provider.component';
import { ProfileComponent } from 'src/service-providers/provider/profile/profile.component';
import { ProviderDashboardComponent } from 'src/service-providers/provider/provider-dashboard/provider-dashboard.component';
import { ScheduleComponent } from 'src/service-providers/schedule/schedule.component';
import { LoginComponent } from '../authentication/Components/login/login.component';
import { RegisterPatientComponent } from '../authentication/Components/register-patient/register-patient.component';
import { RegisterServiceProviderComponent } from '../authentication/Components/register-service-provider/register-service-provider.component';
import { AdmindashboardComponent } from '../dashboards/admindashboard/admindashboard.component';
import { NeutraldashboardComponent } from '../dashboards/neutraldashboard/neutraldashboard.component';
import { PatientdashboardComponent } from '../dashboards/patientdashboard/patientdashboard.component';
import { SPdashboardComponent } from '../dashboards/spdashboard/spdashboard.component';
import { PaymentsComponent } from '../payments/payments/payments.component';

const routes: Routes = [];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class HealthrouteRoutingModule {}
