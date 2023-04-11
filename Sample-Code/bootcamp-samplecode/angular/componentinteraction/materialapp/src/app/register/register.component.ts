import { Component } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {

constructor(private snackobj: MatSnackBar)
{

}

  register()
  {
    this.snackobj.open("Registered","User",
    {
      duration:2000
    }
    
    )
  }
}
