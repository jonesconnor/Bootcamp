import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddProviderComponent } from './admin/add-provider/add-provider.component';
import { UpdateProviderComponent } from './admin/update-provider/update-provider.component';
import { ProfileComponent } from './provider/profile/profile.component';
import { ProviderDashboardComponent } from './provider/provider-dashboard/provider-dashboard.component';
import { ScheduleComponent } from './schedule/schedule.component';

const routes: Routes = [
  // { path: 'provider', component: ProviderDashboardComponent },
  // { path: 'provider/profile', component: ProfileComponent },
  // { path: 'admin/add-provider', component: AddProviderComponent },
  // { path: 'admin/update-provider', component: UpdateProviderComponent },
  // { path: 'provider/schedule', component: ScheduleComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class ServiceProvidersRoutingModule {}
