import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  companyName: string = "CGICanada"

  flag: boolean = true;

  callme() {
    alert("Welcome to Angular");
  }
}
