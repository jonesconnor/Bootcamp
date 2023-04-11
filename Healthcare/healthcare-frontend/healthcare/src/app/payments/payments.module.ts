import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AdminPaymentDashboardComponent } from './dashboard/admin-payment-dashboard/admin-payment-dashboard.component';
import { PatientPaymentDashboardComponent } from './dashboard/patient-payment-dashboard/patient-payment-dashboard.component';
import { PaymentDashboardComponent } from './dashboard/payment-dashboard/payment-dashboard.component';
import { ProviderPaymentDashboardComponent } from './dashboard/provider-payment-dashboard/provider-payment-dashboard.component';
import { LoaderComponent } from './loader/loader.component';
import { MainPaymentsComponent } from './main-payments/main-payments.component';
import { PaymentsComponent } from './payments/payments.component';
import { PaymentStatusPipe } from './pipes/payment-status.pipe';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatTableModule } from '@angular/material/table';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatDialogModule } from '@angular/material/dialog';
import { MatIconModule } from '@angular/material/icon';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatCardModule } from '@angular/material/card';
import { SearchPipe } from './pipes/search.pipe';
import { PatientModule } from '../patient/patient.module';

@NgModule({
  declarations: [
    PaymentsComponent,
    LoaderComponent,
    MainPaymentsComponent,
    PatientPaymentDashboardComponent,
    ProviderPaymentDashboardComponent,
    AdminPaymentDashboardComponent,
    PaymentDashboardComponent,
    PaymentStatusPipe,
    SearchPipe,
  ],
  imports: [
    CommonModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    MatTableModule,
    MatButtonModule,
    MatToolbarModule,
    MatIconModule,
    MatTableModule,
    MatDialogModule,
    MatPaginatorModule,
    MatProgressSpinnerModule,
    MatCardModule,
    PatientModule,
    MatFormFieldModule,
    MatInputModule,
  ],
  exports: [
    PaymentsComponent,
    LoaderComponent,
    MainPaymentsComponent,
    PatientPaymentDashboardComponent,
    ProviderPaymentDashboardComponent,
    AdminPaymentDashboardComponent,
    PaymentDashboardComponent,
    PaymentStatusPipe,
    SearchPipe,
  ],
})
export class PaymentsModule {}
