import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'environment';
import { Observable } from 'rxjs';
import { Message } from './models/Message';

@Injectable({
  providedIn: 'root'
})
export class MessagehandlerService {

  constructor(private httpclientobj: HttpClient) { }

  getMessages(): Observable<Message[]> {
    return this.httpclientobj.get<Message[]>(environment.messagesAPI);
  }

  addMessage(message: Message): Observable<Message> {
    return this.httpclientobj.post<Message>(environment.messagesAPI, message);
  }

  deleteMessage(id: number): Observable<Message> {
    const url = `${environment.messagesAPI}/${id}`;
    return this.httpclientobj.delete<Message>(url);
  }

}
