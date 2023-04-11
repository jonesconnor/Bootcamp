import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ReviewsRoutingModule } from './reviews-routing.module';
import { AddReviewComponent } from './add-review/add-review.component';
import { CurrentReviewComponent } from './current-review/current-review.component';
import { ViewReviewComponent } from './view-review/view-review.component';

import { HttpClientModule } from '@angular/common/http';
import { NgbModule, NgbRatingModule } from '@ng-bootstrap/ng-bootstrap';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatSlideToggleModule } from '@angular/material/slide-toggle';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import {MatButtonModule} from '@angular/material/button';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatIconModule} from '@angular/material/icon';
@NgModule({
  declarations: [
    AddReviewComponent,
    CurrentReviewComponent,
    ViewReviewComponent
  ],
  imports: [
    CommonModule,
    ReviewsRoutingModule,
    NgbModule,
    NgbRatingModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    MatSlideToggleModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatToolbarModule,
    MatIconModule, 
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule
  ]
})
export class ReviewsModule { }
