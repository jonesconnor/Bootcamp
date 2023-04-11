import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SelectmedsComponent } from './selectmeds/selectmeds.component';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { FormsModule } from '@angular/forms';
import { PatientModule } from '../patient/patient.module';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatButtonModule } from '@angular/material/button';
import { MatSnackBarModule } from '@angular/material/snack-bar';

@NgModule({
  declarations: [SelectmedsComponent],
  imports: [
    CommonModule,
    MatExpansionModule,
    MatFormFieldModule,
    MatIconModule,
    MatInputModule,
    MatButtonModule,
    FormsModule,
    PatientModule,
    MatProgressSpinnerModule,
    MatSnackBarModule
  ],
  exports: [SelectmedsComponent],
})
export class DoctorprescriptionModule {}
