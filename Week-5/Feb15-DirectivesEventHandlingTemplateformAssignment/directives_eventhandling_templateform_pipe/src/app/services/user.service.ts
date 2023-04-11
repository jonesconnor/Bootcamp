import { Contact } from './../models/Contact';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable} from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private httpclientobj : HttpClient) {
  }

  // Implement addContacts method using HttpClient for a saving a Contacts details
  addContact(contact): Observable<any> {
    return this.httpclientobj.post('http://localhost:3000/contacts', contact, {
      headers: new HttpHeaders({"Content-Type":"application/json"})
    })
  }

  // Implement getAllContactss method using HttpClient for getting all Contacts details
  getAllContacts(): Observable<any> {
    return this.httpclientobj.get<Array<Contact>>('http://localhost:3000/contacts');
  }
}
