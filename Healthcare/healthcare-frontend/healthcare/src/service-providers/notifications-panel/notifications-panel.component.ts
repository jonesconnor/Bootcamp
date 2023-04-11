import { Component, Input, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-notifications-panel',
  templateUrl: './notifications-panel.component.html',
  styleUrls: ['./notifications-panel.component.css'],
})
export class NotificationsPanelComponent {
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
