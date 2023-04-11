import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import Payment from 'src/app/payments/model/Payment';
import { environment } from 'src/environment';

@Injectable({
  providedIn: 'root',
})
export class PaymentServiceService {
  constructor(private httpRequests: HttpClient) {}

  createHeaders(): HttpHeaders {
    const jwtToken = sessionStorage.getItem('bearerToken');
    return new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization: `Bearer ${jwtToken}`,
    });
  }

  updatePayment(payment: Payment): Observable<any> {
    return this.httpRequests.put<Payment>(
      environment.paymentHost + '/payment',
      payment,
      {
        headers: this.createHeaders(),
      }
    );
  }

  createPayment(payment: Payment): Observable<any> {
    return this.httpRequests.post<Payment>(
      environment.paymentHost + '/patient/payment',
      payment,
      {
        headers: this.createHeaders(),
      }
    );
  }

  getPaymentByPayee(payeeId: string): Observable<any> {
    return this.httpRequests.get<Array<Payment>>(
      environment.paymentHost + '/serviceProvider/payments/' + payeeId,

      {
        headers: this.createHeaders(),
      }
    );
  }

  getPaymentByPayer(payerId: string): Observable<any> {
    return this.httpRequests.get<Array<Payment>>(
      environment.paymentHost + '/patient/payments/' + payerId,
      {
        headers: this.createHeaders(),
      }
    );
  }

  verifyPaymentStatus(paymentIntent: string): Observable<any> {
    return this.httpRequests.get<string>(
      environment.paymentHost + '/payment/verificationStatus/' + paymentIntent,
      {
        headers: this.createHeaders(),
      }
    );
  }

  getPaymentToken(totalHours: number, pricePerHour: number): Observable<any> {
    return this.httpRequests.get<string>(
      environment.paymentHost +
        '/payment/token/' +
        totalHours +
        '/' +
        pricePerHour,
      {
        headers: this.createHeaders(),
      }
    );
  }

  getPaymentByReceiptId(receiptId: string): Observable<any> {
    return this.httpRequests.get<Payment>(
      environment.paymentHost + '/general/payment/receipt/' + receiptId,
      {
        headers: this.createHeaders(),
      }
    );
  }

  getPaymentByPaymentToken(paymentToken: string): Observable<any> {
    return this.httpRequests.get<Payment>(
      environment.paymentHost + '/general/payment/paymentToken/' + paymentToken,
      {
        headers: this.createHeaders(),
      }
    );
  }

  getAllPayments(): Observable<any> {
    return this.httpRequests.get<Array<Payment>>(
      environment.paymentHost + '/admin/payments',
      {
        headers: this.createHeaders(),
      }
    );
  }

  refundPaymentByPaymentToken(paymentToken: string): Observable<any> {
    return this.httpRequests.delete<string>(
      environment.paymentHost + '/general/payment/' + paymentToken,
      {
        headers: this.createHeaders(),
      }
    );
  }
}
