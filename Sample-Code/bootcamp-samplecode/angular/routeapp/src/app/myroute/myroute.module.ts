import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HomeComponent } from '../home/home.component';
import { HeaderComponent } from '../header/header.component';
import { DashboardComponent } from '../dashboard/dashboard.component';
import { LoginComponent } from '../login/login.component';
import { RouterModule, Routes } from '@angular/router';
import { ListviewComponent } from '../listview/listview.component';
import { GridviewComponent } from '../gridview/gridview.component';
import { EmployeeComponent } from '../employee/employee.component';
import { MycanactiveGuard } from '../mycanactive.guard';
import { MycanchildGuard } from '../mycanchild.guard';
import { MycandeactGuard } from '../mycandeact.guard';

const routes : Routes=[
  {
    path:'home',
    component:HomeComponent
  },
  {
    path:'header',
    component:HeaderComponent
  },
  {
    path:'dashboard',
    component:DashboardComponent,
    canActivate:[MycanactiveGuard],
    canActivateChild:[MycanchildGuard],
    children:
    [
      {
        path:'listview',  
        component:ListviewComponent,
      outlet:'listOutlet'
      },
      {
        path:'gridview',
        component:GridviewComponent
      },
      {
        path:'employee/:paramrole',
        component:EmployeeComponent
      }
      // { 
      //   path:'',
      //   redirectTo:'gridview',
      //   pathMatch:'full'
      // }
    ]
  },
  {
    path:'login',
    component:LoginComponent,
    canDeactivate:[MycandeactGuard]
  },
  
  {
    path:'',
    redirectTo:'home',
    pathMatch:'full'
  }
  
  
  
  
  ]
  

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    RouterModule.forRoot(routes)
  ],
  exports:[ 
    RouterModule
    
  ]
})
export class MyrouteModule { }
