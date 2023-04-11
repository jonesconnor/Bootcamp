import { Component, OnInit, ViewChild } from '@angular/core';
import { PaymentServiceService } from '../../service/payment-service.service';
import Payment from '../../model/Payment';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { Observable } from 'rxjs/internal/Observable';

@Component({
  selector: 'app-admin-payment-dashboard',
  templateUrl: './admin-payment-dashboard.component.html',
  styleUrls: ['./admin-payment-dashboard.component.css'],
})
export class AdminPaymentDashboardComponent implements OnInit {
  tableColumns = [
    'bookingId',
    'payeeId',
    'payerId',
    'serviceType',
    'hours',
    'pricePerHour',
    'status',
    'actions',
  ];

  payments: Array<Payment> = [];
  searchtext: String = '';
  dataSource!: MatTableDataSource<any>;

  @ViewChild('paginator') paginator!: MatPaginator;
  obs!: Observable<any>;

  constructor(private paymentService: PaymentServiceService) {}

  ngOnInit(): void {
    this.paymentService.getAllPayments().subscribe(
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
