import { Component, ViewChild } from '@angular/core';
import Payment from '../../model/Payment';
import { PaymentServiceService } from '../../service/payment-service.service';
import jwtDecode from 'jwt-decode';
import { RouterService } from 'src/app/healthroute/Service/router.service';
import { PatientserviceService } from 'src/app/patient/patientservice.service';
import { Patient } from 'src/app/patient/Models/patient.model';
import { HttpHeaders } from '@angular/common/http';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { Observable } from 'rxjs/internal/Observable';

@Component({
  selector: 'app-patient-payment-dashboard',
  templateUrl: './patient-payment-dashboard.component.html',
  styleUrls: ['./patient-payment-dashboard.component.css'],
})
export class PatientPaymentDashboardComponent {
  tableColumns = [
    'bookingId',
    'payeeId',
    'serviceType',
    'hours',
    'pricePerHour',
    'status',
    'actions',
  ];

  payments: Array<Payment> = [];
  searchtext: String = '';
  dataSource!: MatTableDataSource<any>;

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  obs!: Observable<any>;

  constructor(
    private paymentService: PaymentServiceService,
    private routerService: RouterService,
    private patientService: PatientserviceService
  ) {}

  createHeaders(): HttpHeaders {
    const jwtToken = sessionStorage.getItem('bearerToken');
    return new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization: `Bearer ${jwtToken}`,
    });
  }

  ngOnInit(): void {
    this.patientService
      .getPatientByEmail(
        this.createHeaders(),
        sessionStorage.getItem('emailId')!
      )
      .subscribe((res: Patient) => {
        this.paymentService.getPaymentByPayer(res.id).subscribe(
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
      });
  }

  completePayment(receiptId: string) {
    this.routerService.openPayment(receiptId);
  }
}
