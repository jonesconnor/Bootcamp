import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddReviewComponent } from './add-review/add-review.component';
import { CurrentReviewComponent } from './current-review/current-review.component';
import { ViewReviewComponent } from './view-review/view-review.component';

const routes: Routes = [
  {path:"", component:AddReviewComponent},
  {path:"viewReview", component:ViewReviewComponent},
  {path:"viewCurrentReview", component: CurrentReviewComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ReviewsRoutingModule { }
