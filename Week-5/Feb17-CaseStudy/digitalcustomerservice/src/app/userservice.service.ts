import { environment } from './../../environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from './models/User';

@Injectable({
  providedIn: 'root'
})
export class UserserviceService {

  constructor(private httpclientobj: HttpClient) { }

  getUsers(): Observable<any> {
    return this.httpclientobj.get<Array<User>>(environment.usersAPI);
  }

  addUser(user: User): Observable<any> {
    return this.httpclientobj.post(environment.usersAPI, user, {
      headers: new HttpHeaders({"Content-Type":"application/json"})
    });
  }

}
