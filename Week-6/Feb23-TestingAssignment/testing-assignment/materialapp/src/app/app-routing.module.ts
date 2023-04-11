import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from './dashboard/dashboard.component';
import { DboardcanactiveGuard } from './dboardcanactive.guard';
import { HeaderComponent } from './header/header.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';

const routes : Routes=[
  
  {
    path:'header',
    component:HeaderComponent
  },
  {
    path:'dashboard',
    component:DashboardComponent,
    canActivate:[DboardcanactiveGuard]
  },
  {
    path:'login',
    component:LoginComponent,
  },
  {
    path:'register',
    component: RegisterComponent
  },
  {
    path:'',
    redirectTo:'register',
    pathMatch:'full'
  }
  
  
  
  
  ]

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})



export class AppRoutingModule { }
