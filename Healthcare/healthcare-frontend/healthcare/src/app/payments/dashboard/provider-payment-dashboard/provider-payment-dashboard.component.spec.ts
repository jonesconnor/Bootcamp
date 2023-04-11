import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProviderPaymentDashboardComponent } from './provider-payment-dashboard.component';

describe('ProviderPaymentDashboardComponent', () => {
  let component: ProviderPaymentDashboardComponent;
  let fixture: ComponentFixture<ProviderPaymentDashboardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProviderPaymentDashboardComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProviderPaymentDashboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
