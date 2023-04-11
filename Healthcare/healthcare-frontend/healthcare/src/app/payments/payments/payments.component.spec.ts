import {
  HttpClientTestingModule,
  HttpTestingController,
} from '@angular/common/http/testing';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterTestingModule } from '@angular/router/testing';
import { PaymentsComponent } from './payments.component';

describe('PaymentsComponent', () => {
  let component: PaymentsComponent;
  let fixture: ComponentFixture<PaymentsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [
        RouterTestingModule,
        HttpClientTestingModule,
        FormsModule,
        ReactiveFormsModule,
      ],
      declarations: [PaymentsComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(PaymentsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it(`should incude details and payment sections`, () => {
    const app = fixture.componentInstance;
    const compiled = fixture.nativeElement as HTMLElement;
    const headers = compiled.querySelectorAll('.payment-header');
    expect(headers[0].textContent).toContain('Details:');
    expect(headers[1].textContent).toContain('Payment:');
  });

  it(`details should give service prices and details`, () => {
    const app = fixture.componentInstance;
    const compiled = fixture.nativeElement as HTMLElement;
    const headers = compiled.querySelectorAll('.info-title');
    expect(headers[0].textContent).toContain('Service type');
    expect(headers[1].textContent).toContain('Number of hours');
    expect(headers[2].textContent).toContain('Price per hour');
    expect(headers[3].textContent).toContain('Total');
  });
});
