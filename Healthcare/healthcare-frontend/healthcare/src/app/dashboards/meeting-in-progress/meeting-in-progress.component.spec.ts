import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MeetingInProgressComponent } from './meeting-in-progress.component';

describe('MeetingInProgressComponent', () => {
  let component: MeetingInProgressComponent;
  let fixture: ComponentFixture<MeetingInProgressComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MeetingInProgressComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MeetingInProgressComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
