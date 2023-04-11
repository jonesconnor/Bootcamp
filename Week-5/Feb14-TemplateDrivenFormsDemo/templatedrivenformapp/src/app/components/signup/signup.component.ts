import { Component } from '@angular/core';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent {

  user: User
  courses:Array<String>;
  constructor() {
    this.user = new User();
    this.courses = ['Full Stack Development Using Java', 'Full Stack Development Using .NET', 'Full Stack Development Using MERN']
  }


}
