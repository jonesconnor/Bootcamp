import { Component, ViewChild } from '@angular/core';
import { PaymentServiceService } from '../../service/payment-service.service';
import { RouterService } from 'src/app/healthroute/Service/router.service';
import Payment from '../../model/Payment';
import { ProvidersService } from 'src/service-providers/services/providers.service';
import { ServiceProvider } from 'src/service-providers/models/serviceprovider';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { Observable } from 'rxjs/internal/Observable';

@Component({
  selector: 'app-provider-payment-dashboard',
  templateUrl: './provider-payment-dashboard.component.html',
  styleUrls: ['./provider-payment-dashboard.component.css'],
})
export class ProviderPaymentDashboardComponent {
  tableColumns = [
    'bookingId',
    'payerId',
    'serviceType',
    'hours',
    'pricePerHour',
    'status',
    'actions',
  ];

  payments: Array<Payment> = [];
  searchtext: String = '';
  dataSource = new MatTableDataSource<Array<any>>();

  @ViewChild('paginator') paginator!: MatPaginator;
  obs!: Observable<any>;

  constructor(
    private paymentService: PaymentServiceService,
    private providerService: ProvidersService
  ) {}

  ngOnInit(): void {
    this.providerService
      .getServiceProvidersByEmail(sessionStorage.getItem('emailId')!)
      .subscribe(
        (res: ServiceProvider) => {
          this.paymentService.getPaymentByPayee(res.id!).subscribe(
            (res) => {
              this.payments = res;
              this.dataSource = new MatTableDataSource<any>(this.payments);
              this.dataSource.paginator = this.paginator;
              this.obs = this.dataSource.connect();
            },
            (error) => {
              console.log(error);
            }
          );
        },
        (error) => {}
      );
  }

  refundPayment(paymentToken: string) {
    this.paymentService.refundPaymentByPaymentToken(paymentToken).subscribe(
      (res) => {
        let updatedPayments = [...this.payments];

        //Change the status to cancelled if successful request
        updatedPayments[
          updatedPayments.findIndex(
            (payment) => payment.paymentToken == paymentToken
          )
        ].status = 1;

        this.payments = updatedPayments;
        this.dataSource = new MatTableDataSource<any>(this.payments);
        this.dataSource.paginator = this.paginator;
        this.obs = this.dataSource.connect();
      },
      (error) => {
        console.log(error);
      }
    );
  }
}
