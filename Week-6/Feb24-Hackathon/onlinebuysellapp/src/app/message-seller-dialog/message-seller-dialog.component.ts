import { MatSnackBar } from '@angular/material/snack-bar';
import { AuthService } from './../auth.service';
import { MessagehandlerService } from './../messagehandler.service';
import { Component, Inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Message } from '../models/Message';

@Component({
  selector: 'app-message-seller-dialog',
  templateUrl: './message-seller-dialog.component.html',
  styleUrls: ['./message-seller-dialog.component.css']
})
export class MessageSellerDialogComponent {

  messageForm!: FormGroup;
  messages: Message[] = [];

  constructor(private formBuilder: FormBuilder, public dialogRef: MatDialogRef<MessageSellerDialogComponent>, private messagehandler: MessagehandlerService,
    @Inject(MAT_DIALOG_DATA) public data: any, private auth: AuthService, private snackBar: MatSnackBar) {
    this.messageForm = this.formBuilder.group({
      message:['', Validators.required]
    });
  }

  sendMessage() {
    const messageToSend: Message = {
      buyerId: this.auth.getCurrentUserValue().id,
      sellerId: this.data.sellerId,
      productId: this.data.productId,
      message: this.messageForm.value.message
    };
    this.messagehandler.addMessage(messageToSend).subscribe(() => {
      this.messageForm.reset();
      this.dialogRef.close();
      this.snackBar.open('Message Sent', 'Close', {
        duration: 5000
      });
    }, () => {
      this.snackBar.open('Message Failed To Send', 'Close', {
        duration: 5000
      });
    });

  }

  cancel() {
    this.messageForm.reset();
    this.dialogRef.close();
  }
}
