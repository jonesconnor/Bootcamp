import { Component } from '@angular/core';
import { ReviewserviceService } from '../reviewservice.service';
import { NgbRatingConfig } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-current-review',
  templateUrl: './current-review.component.html',
  styleUrls: ['./current-review.component.css']
})
export class CurrentReviewComponent {
  review:any;
  id:any;
  constructor(private http:ReviewserviceService , config: NgbRatingConfig)
  {
    config.max = 5;
    config.readonly = true;
  
    this.getCurrentReview(localStorage.getItem("currentID"));
   
  }

  getCurrentReview(id:any)
  {
    this.http.getspecificReview(id).subscribe(res=>{
      this.review = res;
      console.log(this.review);
    });

  }
}
