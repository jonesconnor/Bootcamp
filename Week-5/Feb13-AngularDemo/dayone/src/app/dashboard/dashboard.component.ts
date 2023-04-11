import { Component } from '@angular/core';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent {
color: string = "green";

courses: Array<String> = ["Addition", "Subtraction", "Multiplication", "Division"];

showDetail(name: String) {
  if (name.length < 3) {
    alert("Invalid Name")
  } else {
    alert(`Welcome ${name}`);
  }
}
}
