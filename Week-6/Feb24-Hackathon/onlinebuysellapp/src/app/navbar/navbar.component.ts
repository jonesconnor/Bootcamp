import { AuthService } from './../auth.service';
import { Component, OnInit } from '@angular/core';
import { User } from '../models/User';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  currentUser!: User;

  constructor(private auth: AuthService) {}

  ngOnInit(): void {
    this.auth.currentUser.subscribe(user => {
      this.currentUser = user;
    })
  }

  logout() {
    this.auth.logout();
  }

}
