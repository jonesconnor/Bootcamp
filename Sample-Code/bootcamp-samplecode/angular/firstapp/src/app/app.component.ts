import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title: string  = 'CGI Canada';

  count : number =10;
  color : string = "white"
  usernames : Array<string>=["Karl","Joyson","Lawrence","Fareena"]

  flag:boolean=false;

  isValid:boolean=false
  
  data : string = "<b> Overall 100Million Sites being deployed using Angular </b>"

  displayDate() : string
  {
    return "13th Feb 20223";
  }

  showDetails()
  {
  //  alert("Hey, We are working with V14.. of Angular which is latest ");
    this.color="blue"
    this.data="Thanks for visiting"
  }
  
  validate(n:string)
  {
     alert("Hai " + n + " You have been Registerd");
  }


}
