import { MyguardGuard } from './../myguard.guard';
import { EmployeeComponent } from './../employee/employee.component';
import { GridviewComponent } from './../gridview/gridview.component';
import { ListviewComponent } from './../listview/listview.component';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from '../dashboard/dashboard.component';
import { HeaderComponent } from '../header/header.component';
import { HomeComponent } from '../home/home.component';
import { LoginComponent } from '../login/login.component';

const routes : Routes = [
  {
    path:'home',
    component: HomeComponent
  },
  {
    path:'header',
    component: HeaderComponent
  },
  {
    path:'dashboard',
    component: DashboardComponent,
    canActivate:[MyguardGuard],
    children: [
      {
        path:'listview',
        component: ListviewComponent,
        outlet: 'listOutlet'
      },
      {
        path:'gridview',
        component: GridviewComponent
      },
      {
        path:'employee/:paramrole',
        component: EmployeeComponent
      },
      {
        path:'',
        redirectTo:'gridview',
        pathMatch:'full'
      }
    ]
  },
  {
    path:'login',
    component: LoginComponent
  }
]

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    RouterModule.forRoot(routes)
  ],
  exports:[RouterModule]
})
export class MyrouteModule { }
