import { User } from './model/user';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserprocessService {

  constructor() { }

  validate (user : User) {
    if (user.username === "admin" && user.password === "password") {
      alert("Valid user");
    } else {
      alert("Invalid user");
    }
  }

}
