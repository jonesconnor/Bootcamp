import { Component } from '@angular/core';
import { ProvidersService }  from 'src/service-providers/services/providers.service';
import { ServiceProvider } from 'src/service-providers/models/serviceprovider';
import { AuthServiceService } from 'src/app/authentication/Service/auth-service.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { RouterService } from 'src/app/healthroute/Service/router.service';
import { ReviewserviceService } from 'src/app/reviews/reviewservice.service';
import { ReviewModel } from 'src/app/reviews/reviews.model';

@Component({
  selector: 'app-searchprovider',
  templateUrl: './searchprovider.component.html',
  styleUrls: ['./searchprovider.component.css']
})
export class SearchproviderComponent {
serviceproviders: Array<ServiceProvider>= []
openbooking=false
clickedprovider!:ServiceProvider;
searchText=""
role=sessionStorage.getItem('Role')
avgRating = 0;

  constructor(private provservice: ProvidersService,private authservice:AuthServiceService,private providerservice:ProvidersService, private snackbar:MatSnackBar,private router:RouterService,private reviewservice:ReviewserviceService){

    provservice.getAllDoctors().subscribe({
      next: (res) => {
        res.forEach((prov=> {
          this.reviewservice.getRevieweeReviews(prov.id!).subscribe(
            res=>{
              this.avgRating=0;
              res.forEach((review:ReviewModel)=>{
                this.avgRating+=review.rating!;
              });
              this.avgRating= this.avgRating/res.length||0;
              console.log("inside get all Ds after getProviderRatingCall: "+this.avgRating);
              prov.averageRating = Number(this.avgRating.toFixed(1));
              this.serviceproviders.push(prov)
            });

        }))
      }, error: (error: any) => {
        console.log(error); },
      });
    provservice.getAllCaretakers().subscribe({
      next: (res) => {
        res.forEach((prov=> this.serviceproviders.push(prov)))
      }, error: (error: any) => {
        console.log(error); },
      });
  }
  goToSPReviews(spId:string){
    this.router.openViewAllRevieweeReviews(spId);
  }
  getProviderRating(spId:string){
    this.avgRating =0;
    this.reviewservice.getRevieweeReviews(spId).subscribe(
      res=>{
        res.forEach((rev:ReviewModel)=>{
          this.avgRating = this.avgRating+rev.rating!;
          console.log(this.avgRating);
        })
        this.avgRating = this.avgRating/res.length ||0;
      },
      err=>{
        console.log("error getting rating");
        this.avgRating=0;
      }
    );
  }
  deleteSP(fName:string,lName:string,mailid:string,spId:string){

    if(confirm("Are you sure you wish to delete Service Provider: "+fName+" "+lName)){

    this.providerservice.deleteServiceProvider(spId).subscribe(

    (res:any)=>{

    this.serviceproviders=this.serviceproviders.filter(sp=>sp.id!=spId);

    this.snackbar.open("Service Provider Deleted","X",{

    duration:1000

    });

    },

    (err:any)=>{

     console.log(err);

    }

    )

    }

    }

  onclose(state: boolean) {
    this.openbooking =state;
    }
}
