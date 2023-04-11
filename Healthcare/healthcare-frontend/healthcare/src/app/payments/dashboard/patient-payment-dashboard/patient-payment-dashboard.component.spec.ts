import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PatientPaymentDashboardComponent } from './patient-payment-dashboard.component';

describe('PatientPaymentDashboardComponent', () => {
  let component: PatientPaymentDashboardComponent;
  let fixture: ComponentFixture<PatientPaymentDashboardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PatientPaymentDashboardComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PatientPaymentDashboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
