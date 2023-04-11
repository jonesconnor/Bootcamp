import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ReviewserviceService } from 'src/app/reviews/reviewservice.service';
import { NgForm} from '@angular/forms';
import { ReviewModel } from '../reviews.model';
import { ProvidersService } from 'src/service-providers/services/providers.service';
import { RouterService } from 'src/app/healthroute/Service/router.service';

@Component({
  selector: 'app-add-review',
  templateUrl: './add-review.component.html',
  styleUrls: ['./add-review.component.css']
})
export class AddReviewComponent {
  comment = "";
  rating:number = 1;
  disable = false;
  spId:string;

  newReview:ReviewModel = new ReviewModel();
  constructor(private http:ReviewserviceService,
    private route:ActivatedRoute,
    private spservice:ProvidersService,
    private router:RouterService
    )
  {
    this.spId=this.route.snapshot.paramMap.get('spId')!;
  }

  sendReview(form:NgForm)
  {
    this.newReview.comment = form.value.comment;
    this.newReview.rating = this.rating;
    this.newReview.revieweeId = this.spId;
    this.newReview.reviewerId = sessionStorage.getItem('userId');
    console.log(this.newReview);
    this.http.addReview(this.newReview).subscribe((res:ReviewModel) => {
      console.log("Review Added");
      this.router.openLanding();
      // this.http.getRevieweeReviews(this.spId).subscribe(
      //   res=>{
      //     let avg=0;
      //     res.forEach((r:ReviewModel)=>{
      //       avg= avg +r.rating!;
      //     });
      //     avg = avg/res.length;
      //     this.spservice.getServiceProviderById(this.spId).subscribe(
      //       res=>{
      //         res.averageRating=avg;
      //         this.spservice.updateServiceProvider(this.spId,res).subscribe(
      //           res=>{
      //             console.log("SUCCESS")
      //           }
      //         );
      //       }
      //     );
      //   }
      // );
    },
    (err:any)=>{
      console.log("Error Adding Review");
    });
    this.disable = true;
  }
}
