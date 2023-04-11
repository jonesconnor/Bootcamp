import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MainPaymentsComponent } from './main-payments.component';

describe('MainPaymentsComponent', () => {
  let component: MainPaymentsComponent;
  let fixture: ComponentFixture<MainPaymentsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MainPaymentsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MainPaymentsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
