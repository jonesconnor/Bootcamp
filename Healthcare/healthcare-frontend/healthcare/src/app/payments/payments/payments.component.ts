import { Component, Input, OnInit } from '@angular/core';
import { PaymentServiceService } from 'src/app/payments/service/payment-service.service';
import { environment } from 'src/environment';
import { loadStripe } from '@stripe/stripe-js';
import { Booking } from 'src/app/booking/model/Booking';
import { Router } from '@angular/router';
import { RouterService } from 'src/app/healthroute/Service/router.service';

@Component({
  selector: 'app-payments',
  templateUrl: './payments.component.html',
  styleUrls: ['./payments.component.css'],
})
export class PaymentsComponent implements OnInit {
  @Input()
  bookingInfo!: Booking;

  @Input()
  error: string = '';

  @Input()
  provider: { specialization: string; fee: number };

  @Input()
  totalPrice: number;

  @Input()
  totalHours: number;

  stripe: any;
  lockPayment: boolean = false;
  elements: any;

  payment: any;

  constructor(
    private router: RouterService,
    private paymentService: PaymentServiceService
  ) {
    this.totalPrice = 0;
    this.totalHours = 0;
    this.provider = {
      specialization: 'Loading...',
      fee: 0,
    };
  }

  ngOnInit(): void {
    //this.setUpPayment();

    this.initializeStripe();
  }

  initializeStripe = async () => {
    this.stripe = await loadStripe(environment.stripePk);
    this.elements = this.stripe.elements({
      clientSecret: this.bookingInfo.paymentToken,
    });
    this.payment = this.elements.create('payment', {
      fields: {
        billingDetails: {
          name: 'auto',
        },
      },
    });
    this.payment.mount('#payment-element');
  };

  async handleSubmit(form: any) {
    this.lockPayment = true;

    const { error } = await this.stripe.confirmPayment({
      elements: { ...this.elements },
      redirect: 'if_required',
    });

    if (error) {
      if (error.type === 'card_error' || error.type === 'validation_error') {
        this.error = error.message;
      } else {
        this.error = 'An unexpected. Please reload the page or try again later';
      }
    } else {
      //verify payment, return back
      this.validatePaymentOnServer(this.bookingInfo.paymentToken!);
    }
  }

  //Makes the server verify the satus of the payment, if the payment was cancelled or confirmed, return back to the main page
  validatePaymentOnServer(paymentToken: string) {
    this.paymentService.verifyPaymentStatus(paymentToken).subscribe(
      (req) => {
        if (req.status === 'CONFIRMED' || req.status === 'CANCELLED') {
          this.router.openPatientDash();
        }
      },
      (error) => {
        this.error = 'Error validating payment';
      }
    );
  }
}
