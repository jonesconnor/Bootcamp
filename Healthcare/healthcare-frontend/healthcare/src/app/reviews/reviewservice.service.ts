import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ReviewsModule } from './reviews.module';
import { ReviewModel } from './reviews.model';

@Injectable({
  providedIn: 'root'
})
export class ReviewserviceService {

  private jsonURL = "http://localhost:9020/api/Reviews";
  
  constructor(private http: HttpClient) 
  {
  }


  addReview(review:ReviewModel) : Observable<any> {
    return this.http.post<any>(this.jsonURL, review);
  }

  updateReview(review:ReviewModel, id:any) : Observable<any> {
    return this.http.put<any>(this.jsonURL + id, review);
  }

  deleteReview(id : number) : Observable<any>
  {
    const url = `${this.jsonURL}/${id}`;
    return this.http.delete(url);
  }
  
  getspecificReview(id:any): Observable<any>
  {
    const url = `${this.jsonURL}/${id}`;
    return this.http.get(url);
  }

  getRevieweeReviews(id:any): Observable<any>
  {
    const url = `${this.jsonURL}/reviewee/${id}`;
    return this.http.get(url);
  }

  getReview(): Observable<any>{
    return this.http.get(this.jsonURL);
  }
}
