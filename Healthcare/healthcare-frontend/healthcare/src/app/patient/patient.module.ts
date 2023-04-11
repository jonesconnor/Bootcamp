import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatExpansionModule } from '@angular/material/expansion';
import { PatientRoutingModule } from './patient-routing.module';
import { PatientprofileComponent } from './patientprofile/patientprofile.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { NewpatientComponent } from './newpatient/newpatient.component';
import { FooterComponent } from './footer/footer.component';
import { SidebarComponent } from './sidebar/sidebar.component';

import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatButtonModule } from '@angular/material/button';
import { MatMenuModule } from '@angular/material/menu';
import { MatInputModule } from '@angular/material/input';
import { MatListModule } from '@angular/material/list';
import { MatBadgeModule } from '@angular/material/badge';
import { HeaderComponent } from './header/header.component';
import { NoteslistComponent } from './noteslist/noteslist.component';
import { NotificationComponent } from './notification/notification.component';
import { PatientdashboardComponent } from './patientdashboard/patientdashboard.component';
import { PatientBookingDetailsComponent } from './patient-booking-details/patient-booking-details.component';
import { MatDialogModule } from '@angular/material/dialog';
import { PrescriptionComponent } from './prescription/prescription.component';
import { MatTableModule } from '@angular/material/table';

@NgModule({
  declarations: [
    PatientprofileComponent,
    NewpatientComponent,
    FooterComponent,
    SidebarComponent,
    HeaderComponent,
    NoteslistComponent,
    NotificationComponent,
    PatientdashboardComponent,
    PatientBookingDetailsComponent,
    PrescriptionComponent,
  ],
  imports: [
    CommonModule,
    PatientRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    MatExpansionModule,
    MatMenuModule,
    MatFormFieldModule,
    MatIconModule,
    MatSnackBarModule,
    MatButtonModule,
    MatInputModule,
    MatListModule,
    MatBadgeModule,
    MatDialogModule,
    MatTableModule
  ],
  exports: [PatientprofileComponent, PatientdashboardComponent,PrescriptionComponent, SidebarComponent],
})
export class PatientModule {}
