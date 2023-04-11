import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginComponent } from './Components/login/login.component';
import { RegisterPatientComponent } from './Components/register-patient/register-patient.component';
import { RegisterServiceProviderComponent } from './Components/register-service-provider/register-service-provider.component';
import {MatCardModule} from '@angular/material/card';
import { HttpClientModule } from '@angular/common/http';
import {MatFormFieldModule} from '@angular/material/form-field';
import { FormsModule} from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import {MatButtonModule} from '@angular/material/button';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import { HealthrouteModule } from '../healthroute/healthroute.module';
import { MatIconModule } from '@angular/material/icon';



@NgModule({
  declarations: [
    LoginComponent,
    RegisterPatientComponent,
    RegisterServiceProviderComponent
  ],
  imports: [
    CommonModule,
    MatCardModule,
    HttpClientModule,
    MatFormFieldModule,
    FormsModule,
    ReactiveFormsModule,
    MatInputModule,
    MatButtonModule,
    MatSnackBarModule,
    HealthrouteModule,
    MatIconModule
  ],
  exports:[
    LoginComponent,
    RegisterPatientComponent,
    RegisterServiceProviderComponent
  ]
})
export class AuthenticationModule { }
