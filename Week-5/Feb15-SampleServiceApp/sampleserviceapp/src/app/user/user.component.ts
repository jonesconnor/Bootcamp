import { Component } from '@angular/core';
import { CommonlibService } from '../commonlib.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent {
  constructor(private serviceobj: CommonlibService) {}

  display(name: string) {
    this.serviceobj.storeDetail(name);
  }

}
