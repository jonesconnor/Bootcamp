import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private httpclient : HttpClient) { }

  generateToken(userdata : any) : Observable<any> {
    return this.httpclient.post("http://localhost:3000/auth/v1", userdata);
  }
}
