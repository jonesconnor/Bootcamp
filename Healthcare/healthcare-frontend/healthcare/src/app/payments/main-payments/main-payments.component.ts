import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Booking } from 'src/app/booking/model/Booking';
import { BookingdbService } from 'src/app/booking/service/bookingdb.service';
import { ServiceProvider } from 'src/service-providers/models/serviceprovider';
import { ProvidersService } from 'src/service-providers/services/providers.service';
import Payment from '../model/Payment';
import { PaymentServiceService } from '../service/payment-service.service';
import { RouterService } from 'src/app/healthroute/Service/router.service';

@Component({
  selector: 'app-main-payments',
  templateUrl: './main-payments.component.html',
  styleUrls: ['./main-payments.component.css'],
})
export class MainPaymentsComponent implements OnInit {
  readonly DAYS_TO_MILISECONDS = 1000 * 60 * 60 * 24;

  isLoading: boolean;
  error: string;
  bookingInfo!: Booking;
  provider!: { specialization: string; fee: number };
  totalPrice: number;
  totalHours: number;
  bookingIdParam!: string;
  statusMessage: string;

  constructor(
    private bookingService: BookingdbService,
    private providerService: ProvidersService,
    private activatedRoute: ActivatedRoute,
    private paymentService: PaymentServiceService,
    private router: RouterService
  ) {
    this.isLoading = true;
    this.error = '';
    this.totalPrice = 0;
    this.totalHours = 0;
    this.statusMessage = '';
  }

  ngOnInit(): void {
    this.setUpPayment();
  }

  setUpPayment = async () => {
    new Promise<void>((resolve, reject) => {
      this.activatedRoute.paramMap.subscribe((params) => {
        this.bookingIdParam = params.get('bookingId')!;
        resolve();
      });
    })
      .then(() => {
        return this.getBookingInfo(this.bookingIdParam);
      })
      .then(() => {
        return this.getServiceProviderInfo(this.bookingInfo.serviceProviderId!);
      })
      .then(() => {
        /*this.totalHours = this.calculateBookingTime(
          new Date(this.bookingInfo.timeSlot!.bookingDate!),
          new Date(this.bookingInfo.recurrenceEndDate!),
          this.bookingInfo.timeSlot!.duration!,
          this.bookingInfo.recurrenceInterval!
        );*/

        this.totalHours = this.calculateBookingTime(
          this.bookingInfo!.timeSlot!.duration!
        );

        this.totalPrice = this.provider.fee * this.totalHours;

        //if a payment token doesnt exist, generate one
        if (this.bookingInfo.paymentToken) {
          //if a previous attempt has been already done, check if it is not completed or cancelled
          return this.validatePaymentOnServer(this.bookingInfo.paymentToken);
        } else {
          return this.generatePaymentToken(this.provider.fee!, this.totalHours);
        }
      });
  };

  getBookingInfo(bookingId: string) {
    return new Promise<void>((resolve, reject) => {
      //booking info goes here
      this.bookingService.viewBooking(bookingId).subscribe(
        (res) => {
          this.bookingInfo = res;
          resolve();
        },
        (error) => {
          this.error = 'Error getting booking information';
          reject();
        }
      );
    });
  }

  getServiceProviderInfo(providerId: string) {
    return new Promise<void>((resolve, reject) => {
      this.providerService
        .getServiceProviderById(providerId)
        .subscribe((res: ServiceProvider) => {
          try {
            this.provider = { ...res };
            resolve();
          } catch (error) {
            this.error = 'Error fetching provider information';
            reject();
          }
        });
    });
  }

  /*calculateBookingTime(
    bookingStartDate: Date,
    bookingEndDate: Date,
    serviceHoursByDay: number,
    recurrenceInterval: number
  ) {*/
  calculateBookingTime(bookingMinutes: number) {
    /*if (recurrenceInterval === 0) {
      return serviceHoursByDay;
    }

    //We add DAYS_TO_MILISECONDS because we want to be sure that the total days to be inclusive of the startDate
    let totalDays: number = Math.floor(
      (bookingEndDate.getTime() -
        bookingStartDate.getTime() +
        this.DAYS_TO_MILISECONDS) /
        this.DAYS_TO_MILISECONDS
    );

    let serviceDays: number = Math.floor(totalDays / recurrenceInterval);
      

    return serviceHoursByDay * serviceDays;*/

    return bookingMinutes / 60;
  }

  generatePaymentToken(doctorFee: number, totalHours: number) {
    new Promise<void>((resolve, reject) => {
      this.paymentService.getPaymentToken(totalHours, doctorFee).subscribe(
        (res) => {
          this.bookingInfo.paymentToken = res.paymentToken;
          //we are going to add an incomplete payment. Status keeps track if the payment has been confirmed
          const newPayment: Payment = {
            receiptId: this.bookingInfo.id!,
            paymentToken: this.bookingInfo.paymentToken,
            serviceType: this.provider.specialization,
            hours: this.totalHours,
            pricePerHour: this.provider.fee,
            status: 0,
            payeeId: this.bookingInfo.serviceProviderId,
            payerId: this.bookingInfo.patientId,
          };

          this.postPayment(newPayment);
          this.updateBooking(this.bookingInfo);
          this.isLoading = false;
          resolve();
        },
        (error) => {
          this.error = 'Error generating payment token';
          reject();
        }
      );
    });
  }

  updateBooking(booking: Booking) {
    this.bookingService.updateBooking(booking).subscribe(
      (res) => {},
      (error) => {
        this.error = 'Error updating booking information';
      }
    );
  }

  //Makes the server verify the satus of the payment, if the payment was cancelled or confirmed, return back to the main page
  validatePaymentOnServer(paymentToken: string) {
    return new Promise<void>((resolve, reject) => {
      this.paymentService.verifyPaymentStatus(paymentToken).subscribe(
        (req) => {
          if (req.status === 'CONFIRMED' || req.status === 'CANCELLED') {
            //this.router.openPatientDash();
            this.statusMessage = req.status;
          } else {
            this.isLoading = false;
          }
          resolve();
        },
        (error) => {
          this.error = 'Error validating payment';
          reject();
        }
      );
    });
  }

  postPayment(newPayment: Payment) {
    this.paymentService.createPayment(newPayment).subscribe(
      (req) => {},
      (error) => {
        this.error = error;
      }
    );
  }
}
