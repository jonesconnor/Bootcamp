import { Component } from '@angular/core';
import { CommonlibService } from '../commonlib.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent {

  constructor(private serviceobj:CommonlibService)
  {

  }

  showDetail(nm:string)
  {
    this.serviceobj.storeDetail(nm);
  }

}
