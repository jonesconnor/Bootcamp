import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-noteslist',
  templateUrl: './noteslist.component.html',
  styleUrls: ['./noteslist.component.css']
})
export class NoteslistComponent {
  @Input() togglePanel: boolean = false; // decorate the property with @Input()
  @Output() panelClosed = new EventEmitter<boolean>();
  @Output() sendNotificationsToHeader = new EventEmitter<number>();

  closePanel() {
    this.panelClosed.emit();
  }

  sendNoOfNotifications(noOfNotifications: number) {
    this.sendNotificationsToHeader.emit(noOfNotifications);
  }


}
