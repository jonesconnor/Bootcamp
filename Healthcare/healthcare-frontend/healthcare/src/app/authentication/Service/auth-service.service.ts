import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { User } from '../Model/User';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthServiceService {
  token:any;

  constructor(private httpobj:HttpClient) {
    this.token = sessionStorage.getItem('bearerToken');
   }

  register(user:User):Observable<User>{
    return this.httpobj.post<User>("http://localhost:1337/auth/register",user,{
      headers: new HttpHeaders({"Content-Type":"application/json"})
    })  
  }
  login(user:User):Observable<Map<string,string>>{
    return this.httpobj.post<Map<string,string>>("http://localhost:1337/auth/login",user,{
      headers: new HttpHeaders({"Content-Type":"application/json"})
    })  
  }
  delete(mailid:string){
    return this.httpobj.delete<boolean>("http://localhost:1337/auth/delete/"+mailid,{
      headers: new HttpHeaders({"Content-Type":"application/json"}).set('Authorization','Bearer '+this.token)
    })
  }
  
}
