import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MessageSellerDialogComponent } from './message-seller-dialog.component';

describe('MessageSellerDialogComponent', () => {
  let component: MessageSellerDialogComponent;
  let fixture: ComponentFixture<MessageSellerDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MessageSellerDialogComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MessageSellerDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
