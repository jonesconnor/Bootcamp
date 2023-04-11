import { Component } from '@angular/core';

import { RouterService } from 'src/app/healthroute/Service/router.service';

@Component({
  selector: 'app-neutraldashboard',
  templateUrl: './neutraldashboard.component.html',
  styleUrls: ['./neutraldashboard.component.css']
})
export class NeutraldashboardComponent {
  constructor(private router:RouterService){}

  patRegister(){
    console.log("router called to patient register page");
    this.router.openPatRegister();
  }

}
