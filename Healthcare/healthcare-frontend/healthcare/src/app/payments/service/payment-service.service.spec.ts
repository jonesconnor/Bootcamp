import {
  HttpClientTestingModule,
  HttpTestingController,
} from '@angular/common/http/testing';
import { async, inject, TestBed } from '@angular/core/testing';
import Payment from 'src/app/payments/model/Payment';
import { environment } from 'src/environment';
import { PaymentServiceService } from './payment-service.service';

describe('PaymentServiceService', () => {
  let service: PaymentServiceService;
  let httpMock: HttpTestingController;
  let postItemArr: Array<Payment>;

  beforeEach(() => {
    TestBed.configureTestingModule({ imports: [HttpClientTestingModule] });
    service = TestBed.inject(PaymentServiceService);
    httpMock = TestBed.inject(HttpTestingController);
    postItemArr = [
      {
        receiptId: 'svdadvaw23fse3w',
        paymentToken:
          'pi_3Mr4q7GoYEfXSxkZ0MpiP74U_secret_SN4qkqAV5Ni9DswNv2IrTzkXO',
        serviceType: 'doctor',
        hours: 3,
        pricePerHour: 3,
        status: 0,
        payeeId: '1',
        payerId: '1',
      },
      {
        receiptId: 'svdadvaw23fse4y',
        paymentToken:
          'pi_3Mr4rhGoYEfXSxkZ05gPNJ9J_secret_2mVzxV3ruA7mjAc9HmWRhLwwe',
        serviceType: 'care taker',
        hours: 4,
        pricePerHour: 5,
        status: 0,
        payeeId: 'providerExampleID',
        payerId: 'payerExampleID',
      },
    ];
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should be able to post a payment', async(
    inject(
      [HttpTestingController, PaymentServiceService],
      (httpClient: HttpTestingController, service: PaymentServiceService) => {
        service.createPayment(postItemArr[0]).subscribe((res) => {
          expect(res).toBe(postItemArr[0]);
        });
        let req = httpMock.expectOne(
          environment.paymentHost + '/patient/payment'
        );
        expect(req.request.method).toBe('POST');
        req.flush(postItemArr[0]);
        httpMock.verify();
      }
    )
  ));

  it('should be able to update a payment', async(
    inject(
      [HttpTestingController, PaymentServiceService],
      (httpClient: HttpTestingController, service: PaymentServiceService) => {
        postItemArr[1].serviceType = 'test';

        service.updatePayment(postItemArr[1]).subscribe((res) => {
          expect(res.serviceType).toBe('test');
        });

        let req = httpMock.expectOne(
          environment.paymentHost + '/patient/payment'
        );
        expect(req.request.method).toBe('PUT');
        req.flush(postItemArr[1]);
        httpMock.verify();
      }
    )
  ));

  it('should be able to get all service provider payments', async(
    inject(
      [HttpTestingController, PaymentServiceService],
      (httpClient: HttpTestingController, service: PaymentServiceService) => {
        service.getPaymentByPayee('providerExampleID').subscribe((res) => {
          expect(res.length).toBeGreaterThanOrEqual(1);
        });
        let req = httpMock.expectOne(
          environment.paymentHost +
            '/serviceProvider/payments/providerExampleID'
        );
        expect(req.request.method).toBe('GET');
        req.flush(postItemArr);
        httpMock.verify();
      }
    )
  ));

  it('should be able to get all patient payments', async(
    inject(
      [HttpTestingController, PaymentServiceService],
      (httpClient: HttpTestingController, service: PaymentServiceService) => {
        service.getPaymentByPayer('payerExampleID').subscribe((res) => {
          expect(res.length).toBeGreaterThanOrEqual(1);
        });
        let req = httpMock.expectOne(
          environment.paymentHost + '/patient/payments/payerExampleID'
        );
        expect(req.request.method).toBe('GET');
        req.flush(postItemArr);
        httpMock.verify();
      }
    )
  ));

  it('should be able to get a payment by receiptId', async(
    inject(
      [HttpTestingController, PaymentServiceService],
      (httpClient: HttpTestingController, service: PaymentServiceService) => {
        service.getPaymentByReceiptId('svdadvaw23fse4y').subscribe((res) => {
          expect(res[1]).toBe(postItemArr[1]);
        });
        let req = httpMock.expectOne(
          environment.paymentHost + '/general/payment/receipt/svdadvaw23fse4y'
        );
        expect(req.request.method).toBe('GET');
        req.flush(postItemArr);
        httpMock.verify();
      }
    )
  ));

  it('should be able to get a payment by paymentToken', async(
    inject(
      [HttpTestingController, PaymentServiceService],
      (httpClient: HttpTestingController, service: PaymentServiceService) => {
        service
          .getPaymentByPaymentToken(
            'pi_3Mr4rhGoYEfXSxkZ05gPNJ9J_secret_2mVzxV3ruA7mjAc9HmWRhLwwe'
          )
          .subscribe((res) => {
            expect(res[1]).toBe(postItemArr[1]);
          });
        let req = httpMock.expectOne(
          environment.paymentHost +
            '/general/payment/paymentToken/pi_3Mr4rhGoYEfXSxkZ05gPNJ9J_secret_2mVzxV3ruA7mjAc9HmWRhLwwe'
        );
        expect(req.request.method).toBe('GET');
        req.flush(postItemArr);
        httpMock.verify();
      }
    )
  ));

  it('should be able to get all payments', async(
    inject(
      [HttpTestingController, PaymentServiceService],
      (httpClient: HttpTestingController, service: PaymentServiceService) => {
        service.getAllPayments().subscribe((res) => {
          expect(res.length).toBeGreaterThanOrEqual(1);
        });
        let req = httpMock.expectOne(
          environment.paymentHost + '/admin/payments'
        );
        expect(req.request.method).toBe('GET');
        req.flush(postItemArr);
        httpMock.verify();
      }
    )
  ));
});
