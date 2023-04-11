import { Component, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from './dashboard/dashboard.component';
import { DboardcanactiveGuard } from './dboardcanactive.guard';
import { EmpviewComponent } from './empview/empview.component';
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
    canActivate:[DboardcanactiveGuard],
    children:[
      {
      path:'empview/:paramname',
      component:EmpviewComponent

      },
      {
        path:'',
        redirectTo:'empview',
        pathMatch:'full'
      }
    ]
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
    redirectTo:'login',
    pathMatch:'full'
  }
  
  
  
  
  ]

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})



export class AppRoutingModule { }
