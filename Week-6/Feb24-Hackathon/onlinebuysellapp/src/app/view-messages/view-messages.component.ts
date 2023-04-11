import { MessagehandlerService } from './../messagehandler.service';
import { Component, OnInit } from '@angular/core';
import { Message } from '../models/Message';
import { User } from '../models/User';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-view-messages',
  templateUrl: './view-messages.component.html',
  styleUrls: ['./view-messages.component.css']
})
export class ViewMessagesComponent implements OnInit{

  currentUser!: User;
  messages: Message[] = []

  constructor(private messagehandler: MessagehandlerService, private auth: AuthService) {}

  ngOnInit(): void {
    this.auth.currentUser.subscribe((user: User) => {
      this.currentUser = user;
    });
    this.loadMessages();
  }

  loadMessages() {
    this.messagehandler.getMessages().subscribe((messages: Message[]) => {
      const filteredMessages = messages.filter((message: Message) => message.sellerId === this.currentUser.id);
      this.messages = filteredMessages;
    });
  }

}
