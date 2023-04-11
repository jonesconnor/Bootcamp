import { Component } from '@angular/core';
import { CommonlibService } from '../commonlib.service';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']

})
export class AdminComponent {

  constructor(private commonobj : CommonlibService)  // injecting service inside the component
  {

  }
  
   display(salary:any)
   {
    let result= this.commonobj.displayHike("admin",salary)
    alert(result)
   }

}
