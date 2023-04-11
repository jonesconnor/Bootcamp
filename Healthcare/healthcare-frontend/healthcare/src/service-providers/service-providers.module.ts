import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';

import { FullCalendarModule } from '@fullcalendar/angular';
import { MatListModule } from '@angular/material/list';
import { MatIconModule } from '@angular/material/icon';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatBadgeModule } from '@angular/material/badge';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { MatSelectModule } from '@angular/material/select';
import { MatDialogModule } from '@angular/material/dialog';
import {MatExpansionModule} from '@angular/material/expansion';

import { ServiceProvidersRoutingModule } from './service-providers-routing.module';
import { HeaderComponent } from './header/header.component';
import { UpdateProviderComponent } from './admin/update-provider/update-provider.component';
import { FooterComponent } from './footer/footer.component';
import { ProfileComponent } from './provider/profile/profile.component';
import { ScheduleComponent } from './schedule/schedule.component';
import { NotificationComponent } from './notification/notification.component';
import { NotificationsPanelComponent } from './notifications-panel/notifications-panel.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AddProviderComponent } from './admin/add-provider/add-provider.component';
import { ProviderDashboardComponent } from './provider/provider-dashboard/provider-dashboard.component';
import { SideNavComponent } from './provider/side-nav/side-nav.component';
import { MatToolbarModule } from '@angular/material/toolbar';
import { UpdatebookingComponent } from './updatebooking/updatebooking.component';
import { BookingDetailsComponent } from './booking-details/booking-details.component';

@NgModule({
  declarations: [
    HeaderComponent,
    UpdateProviderComponent,
    FooterComponent,
    ProfileComponent,
    ScheduleComponent,
    NotificationComponent,
    NotificationsPanelComponent,
    AddProviderComponent,
    ProviderDashboardComponent,
    SideNavComponent,
    UpdatebookingComponent,
    BookingDetailsComponent,
  ],
  imports: [
    CommonModule,
    ServiceProvidersRoutingModule,
    MatListModule,
    MatIconModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatSnackBarModule,
    MatBadgeModule,
    MatDialogModule,
    HttpClientModule,
    FormsModule,
    MatToolbarModule,
    FullCalendarModule,
    ReactiveFormsModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatSelectModule,
    MatExpansionModule,
  ],
  exports: [
    HeaderComponent,
    UpdateProviderComponent,
    FooterComponent,
    AddProviderComponent,
    ProfileComponent,
    UpdatebookingComponent,
    SideNavComponent,
  ],
})
export class ServiceProvidersModule {}
