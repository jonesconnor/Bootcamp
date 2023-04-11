import { LoginBlockGuard } from './login-block.guard';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import {MatCardModule} from '@angular/material/card';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatSelectModule} from '@angular/material/select';
import {MatOptionModule} from '@angular/material/core';
import { RegistrationComponent } from './registration/registration.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { BuyerdashComponent } from './buyerdash/buyerdash.component';
import { SellerdashComponent } from './sellerdash/sellerdash.component';
import {MatButtonModule} from '@angular/material/button';
import { MatInputModule } from '@angular/material/input';
import { ProductlistingComponent } from './productlisting/productlisting.component';
import {MatTableModule} from '@angular/material/table';
import { ViewwishlistComponent } from './viewwishlist/viewwishlist.component';
import { MessageSellerDialogComponent } from './message-seller-dialog/message-seller-dialog.component';
import {MatDialogModule} from '@angular/material/dialog';
import { NavbarComponent } from './navbar/navbar.component';
import {MatToolbarModule} from '@angular/material/toolbar';
import { SellerProductListingComponent } from './seller-product-listing/seller-product-listing.component';
import { ViewMessagesComponent } from './view-messages/view-messages.component';
import { AddProductDialogComponent } from './add-product-dialog/add-product-dialog.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegistrationComponent,
    BuyerdashComponent,
    SellerdashComponent,
    ProductlistingComponent,
    ViewwishlistComponent,
    MessageSellerDialogComponent,
    NavbarComponent,
    SellerProductListingComponent,
    ViewMessagesComponent,
    AddProductDialogComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatCardModule,
    MatFormFieldModule,
    FormsModule,
    HttpClientModule,
    MatSnackBarModule,
    MatSelectModule,
    MatOptionModule,
    ReactiveFormsModule,
    MatButtonModule,
    MatInputModule,
    MatTableModule,
    MatDialogModule,
    MatToolbarModule

  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
