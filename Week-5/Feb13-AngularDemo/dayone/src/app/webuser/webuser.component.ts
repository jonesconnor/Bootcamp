import { Component } from '@angular/core';
import { User } from '../model/user';
import { UserprocessService } from '../userprocess.service';

@Component({
  selector: 'app-webuser',
  templateUrl: './webuser.component.html',
  styleUrls: ['./webuser.component.css']
})
export class WebuserComponent {

  usernew: User;

  constructor(private userprocessservice : UserprocessService) {
    this.usernew = new User();
  }

  signIn() {
    this.userprocessservice.validate(this.usernew);
  }


}
