import { AdminDashboardComponent } from './admin-dashboard/admin-dashboard.component';
import { ViewQueryComponent } from './view-query/view-query.component';
import { RegistrationComponent } from './registration/registration.component';
import { LoginComponent } from './login/login.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CustomerDashboardComponent } from './customer-dashboard/customer-dashboard.component';

const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegistrationComponent },
  {
    path: 'customerdashboard',
    component:CustomerDashboardComponent,
    children: [
      { path: 'view-query', component: ViewQueryComponent }
    ]
  },
  { path: 'admindashboard', component: AdminDashboardComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
