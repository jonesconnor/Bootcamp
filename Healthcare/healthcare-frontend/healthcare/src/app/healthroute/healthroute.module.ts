import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { HealthrouteRoutingModule } from './healthroute-routing.module';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from '../authentication/Components/login/login.component';
import { RegisterPatientComponent } from '../authentication/Components/register-patient/register-patient.component';
import { RegisterServiceProviderComponent } from '../authentication/Components/register-service-provider/register-service-provider.component';
import { AdmindashboardComponent } from '../dashboards/admindashboard/admindashboard.component';
import { NeutraldashboardComponent } from '../dashboards/neutraldashboard/neutraldashboard.component';
import { PatientdashboardComponent } from '../dashboards/patientdashboard/patientdashboard.component';
import { SPdashboardComponent } from '../dashboards/spdashboard/spdashboard.component';
import { AdminGuard } from '../Guards/admin.guard';
import { PatientGuard } from '../Guards/patient.guard';
import { ServiceproviderGuard } from '../Guards/serviceprovider.guard';
import { MedicationviewComponent } from '../medication/medicationview/medicationview.component';
import { SearchproviderComponent } from '../booking/searchprovider/searchprovider.component';
import { AddReviewComponent } from '../reviews/add-review/add-review.component';
import { ViewReviewComponent } from '../reviews/view-review/view-review.component';
import { CurrentReviewComponent } from '../reviews/current-review/current-review.component';
import { NeutralGuard } from '../Guards/neutral.guard';
import { AddProviderComponent } from 'src/service-providers/admin/add-provider/add-provider.component';
import { UpdateProviderComponent } from 'src/service-providers/admin/update-provider/update-provider.component';
import { ProfileComponent } from 'src/service-providers/provider/profile/profile.component';
import { ProviderDashboardComponent } from 'src/service-providers/provider/provider-dashboard/provider-dashboard.component';
import { ScheduleComponent } from 'src/service-providers/schedule/schedule.component';
import { MrdisplayComponent } from '../medicalrecord/mrdisplay/mrdisplay.component';
import { PatientprofileComponent } from '../patient/patientprofile/patientprofile.component';
import { UpdatebookingComponent } from 'src/service-providers/updatebooking/updatebooking.component';
import { MeetingInProgressComponent } from '../dashboards/meeting-in-progress/meeting-in-progress.component';
import { PostapptdialogComponent } from '../booking/postapptdialog/postapptdialog.component';
import { MainPaymentsComponent } from '../payments/main-payments/main-payments.component';
import { PaymentDashboardComponent } from '../payments/dashboard/payment-dashboard/payment-dashboard.component';
import { SelectmedsComponent } from '../doctorprescription/selectmeds/selectmeds.component';
import { PrescriptionComponent } from '../patient/prescription/prescription.component';

const routes: Routes = [
  {
    path: '',
    redirectTo: 'landing',
    pathMatch: 'full',
  },
  {
    path: 'login',
    component: LoginComponent,
    canActivate: [NeutralGuard],
  },
  {
    path: 'patientregister',
    component: RegisterPatientComponent,
  },
  {
    path: 'spregister',
    component: RegisterServiceProviderComponent,
    canActivate: [AdminGuard],
  },
  {
    path: 'landing',
    component: NeutraldashboardComponent,
    canActivate: [NeutralGuard],
  },
  {
    path: 'patientdash',
    component: PatientdashboardComponent,
    canActivate: [PatientGuard],
  },
  {
    path: 'spdash',
    component: SPdashboardComponent,
    canActivate: [ServiceproviderGuard],
  },
  {
    path: 'admindash',
    component: AdmindashboardComponent,
    canActivate: [AdminGuard],
  },
  {
    path: 'providers',
    component: SearchproviderComponent,
  },
  {
    path: 'reviews',
    component: AddReviewComponent,
  },
  {
    path: 'reviews/viewReview',
    component: ViewReviewComponent,
  },
  {
    path: 'reviews/currentReview',
    component: CurrentReviewComponent,
  },
  {
    path: 'medications',
    component: MedicationviewComponent,
  },
  {
    path: 'provider',
    component: ProviderDashboardComponent,
  },
  {
    path: 'provider/profile',
    component: ProfileComponent,
  },
  {
    path: 'provider/schedule',
    component: ScheduleComponent,
  },
  {
    path: 'admin/add-provider',
    component: AddProviderComponent,
  },
  {
    path: 'admin/update-provider',
    component: UpdateProviderComponent,
  },
  {
    path: 'patientprofile',
    component: PatientprofileComponent,
  },
  {
    path: 'medicalhistory',
    component: MrdisplayComponent,
  },
  {
    path: 'updatebooking/:bookingId',
    component: UpdatebookingComponent,
  },
  {
    path: 'meetinginprogress/:bookingId',
    component: MeetingInProgressComponent,
  },
  {
    path: 'postapptdialog',
    component: PostapptdialogComponent,
  },
  {
    path: 'payment/:bookingId',
    component: MainPaymentsComponent,
    canActivate: [PatientGuard],
  },
  {
    path: 'payments/dashboard',
    component: PaymentDashboardComponent,
  },
  {
    path: 'createprescription/:patientId',
    component: SelectmedsComponent
  },
  { 
    path: 'prescription', 
    component: PrescriptionComponent
  }
];
@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    HealthrouteRoutingModule,
    RouterModule.forRoot(routes),
  ],
  exports: [HealthrouteRoutingModule],
})
export class HealthrouteModule {}
