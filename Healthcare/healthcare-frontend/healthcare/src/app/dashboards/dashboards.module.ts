import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PatientdashboardComponent } from './patientdashboard/patientdashboard.component';
import { SPdashboardComponent } from './spdashboard/spdashboard.component';
import { AdmindashboardComponent } from './admindashboard/admindashboard.component';
import { FullCalendarModule } from '@fullcalendar/angular';
import {MatCardModule} from '@angular/material/card';
import {MatDividerModule} from '@angular/material/divider';
import { MatListModule } from '@angular/material/list';
import { MatIconModule } from '@angular/material/icon';
import { NeutraldashboardComponent } from './neutraldashboard/neutraldashboard.component';
import {MatButtonModule} from '@angular/material/button';
import { HealthrouteModule } from '../healthroute/healthroute.module';
import { ServiceProvidersModule } from 'src/service-providers/service-providers.module';
import { MeetingInProgressComponent } from './meeting-in-progress/meeting-in-progress.component';
import { BookingModule } from '../booking/booking.module';
import { MatDialogModule } from '@angular/material/dialog';
import { DoctorprescriptionModule } from '../doctorprescription/doctorprescription.module';
import {DatePipe} from '@angular/common';
import { PatientModule } from '../patient/patient.module';




@NgModule({
  declarations: [
    PatientdashboardComponent,
    SPdashboardComponent,
    AdmindashboardComponent,
    NeutraldashboardComponent,
    MeetingInProgressComponent
  ],
  imports: [
    CommonModule,
    MatCardModule,
    MatButtonModule,
    HealthrouteModule,
    ServiceProvidersModule,
    FullCalendarModule,
    MatDividerModule,
    MatListModule ,
    MatIconModule,
    BookingModule,
    MatDialogModule,
    DoctorprescriptionModule, 
    PatientModule,
  ],
  exports:[
    AdmindashboardComponent,
    PatientdashboardComponent,
    SPdashboardComponent,
    NeutraldashboardComponent,
  ],
  providers:[
    DatePipe
  ]
})
export class DashboardsModule { }
