import { MatSnackBar } from '@angular/material/snack-bar';
import { RouterService } from './../router.service';
import { Component, OnInit } from '@angular/core';
import { UserhandlerService } from '../userhandler.service';
import { User } from '../models/User';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit{

  user: User;
  users: User[] = [];
  form: any;

  constructor(private userhandler : UserhandlerService, private routeobj: RouterService, private snackBar: MatSnackBar) {
    this.user = new User();
  }

  ngOnInit(): void {
    this.userhandler.getUsers().subscribe(
      data => {
        this.users = data;
      }
    );
  }

  addUser(val: NgForm, event: Event) {
    event.preventDefault();

    const newId = this.users.length > 0 ? (this.users[this.users.length - 1]?.id || 0) + 1 : 1;
    this.user = { id: newId, ...val.value };
    this.userhandler.addUser(this.user).subscribe(
      (res : any) => {
        this.users.push(this.user);
        console.log(res);
        this.snackBar.open('Form submitted successfully', 'Close', {
          duration: 5000
        });
        this.routeobj.openLogin();
      },
      (err : any) => {
        console.log(err);
      }
    )
    val.reset();
  }

}
