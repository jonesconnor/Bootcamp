import { Component } from '@angular/core';
import { NgbRatingConfig } from '@ng-bootstrap/ng-bootstrap';
import { ReviewserviceService } from '../reviewservice.service';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ReviewModel } from '../reviews.model';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-view-review',
  templateUrl: './view-review.component.html',
  styleUrls: ['./view-review.component.css'],
})
export class ViewReviewComponent {
  reviewDataObject: ReviewModel = new ReviewModel();
  allReviewsData: any;
  reviewsNum:number = 0;
  scoreRate:number = 0;
  spId:string='';
  zReviews=true;

  constructor(
    config: NgbRatingConfig,
    private http: ReviewserviceService,
    private fb: FormBuilder,
    private route: ActivatedRoute
  ) {
    config.max = 5;
    config.readonly = true;
    this.spId = this.route.snapshot.paramMap.get('spId')!;
    if (!this.spId) {
      return;
    }
    this.getRevieweeReviews(this.spId);
  }

  getAllReviews() {
    this.http.getReview().subscribe((data) => {
      this.allReviewsData = data;
      this.reviewsNum = Object.keys(this.allReviewsData).length;
      this.scoreRate = Number(
        this.countAverage(this.allReviewsData).toFixed(1)
      );
      console.log(this.allReviewsData);
    });
  }

  getRevieweeReviews(id:any){
    this.http.getRevieweeReviews(id).subscribe(data =>
      {
        if(data.length>0){
          this.zReviews=false;
        }
        else{
          this.zReviews=true;
        }
        this.allReviewsData = data;
        this.reviewsNum = Object.keys(this.allReviewsData).length
        this.scoreRate = Number(this.countAverage(this.allReviewsData).toFixed(1));
        console.log("this is running");
        let total = data.length;
        console.log(total);
        let fivestars = (data.filter((rev:ReviewModel)=>rev.rating===5).length/total).toLocaleString(undefined,{style: 'percent'});
        let fourstars = (data.filter((rev:ReviewModel)=>rev.rating===4).length/total).toLocaleString(undefined,{style: 'percent'});
        let threestars = (data.filter((rev:ReviewModel)=>rev.rating===3).length/total).toLocaleString(undefined,{style: 'percent'});
        let twostars = (data.filter((rev:ReviewModel)=>rev.rating===2).length/total).toLocaleString(undefined,{style: 'percent'});
        let onestar = (data.filter((rev:ReviewModel)=>rev.rating===1).length/total).toLocaleString(undefined,{style: 'percent'});
        
        document.getElementById("fivestar")!.style.width=fivestars;
        document.getElementById("fourstar")!.style.width=fourstars;
        document.getElementById("threestar")!.style.width=threestars;
        document.getElementById("twostar")!.style.width=twostars;
        document.getElementById("onestar")!.style.width=onestar;
      })
  }
  countAverage(data: any) {
    var sum = 0;
    for (let review of data) {
      sum += review.rating;
    }

    const avg = sum / data.length || 0;
    return avg;
  }

  reviewweeId: number = 2;
}
