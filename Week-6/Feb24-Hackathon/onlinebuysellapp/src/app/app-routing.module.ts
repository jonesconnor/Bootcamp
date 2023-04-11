import { LoginBlockGuard } from './login-block.guard';
import { ViewMessagesComponent } from './view-messages/view-messages.component';
import { ViewwishlistComponent } from './viewwishlist/viewwishlist.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BuyerdashComponent } from './buyerdash/buyerdash.component';
import { LoginComponent } from './login/login.component';
import { RegistrationComponent } from './registration/registration.component';
import { SellerdashComponent } from './sellerdash/sellerdash.component';

const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegistrationComponent },
  { path: 'buyer-home', component: BuyerdashComponent, canActivate: [LoginBlockGuard]},
  { path: 'seller-home', component: SellerdashComponent, canActivate: [LoginBlockGuard] },
  { path: 'wishlist', component: ViewwishlistComponent, canActivate: [LoginBlockGuard]},
  { path: 'messages', component: ViewMessagesComponent, canActivate: [LoginBlockGuard]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
