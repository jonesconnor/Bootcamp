import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminPaymentDashboardComponent } from './admin-payment-dashboard.component';

describe('AdminPaymentDashboardComponent', () => {
  let component: AdminPaymentDashboardComponent;
  let fixture: ComponentFixture<AdminPaymentDashboardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminPaymentDashboardComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminPaymentDashboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
