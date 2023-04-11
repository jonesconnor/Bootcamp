import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'firstapp';
  count : number = 11;
  usernames: Array<string> = ['connor', 'alec', 'lance'];

  displayDate() : string {
    return "13th Feb 2023";
  }

  flag: boolean = true;
  isValid: boolean = false;

  validate(s: string) {
    alert(`Hello ${s}`);
  }

}
