import { ActivatedRoute } from '@angular/router';
import { ChangeDetectorRef, Component, ViewChild } from '@angular/core';
import { MedicationService } from 'src/app/medication/medication.service';
import { Medication } from 'src/app/medication/model/medication';
import { Result } from 'src/app/medication/model/result';
import { RouterService } from 'src/app/healthroute/Service/router.service';
import { PatientserviceService } from 'src/app/patient/patientservice.service';
import { Prescription } from 'src/app/patient/Models/prescription.model';
import { HttpHeaders } from '@angular/common/http';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-selectmeds',
  templateUrl: './selectmeds.component.html',
  styleUrls: ['./selectmeds.component.css'],
})
export class SelectmedsComponent {
  med: Medication = new Medication();
  medications: Array<Result>;
  placeholder: Array<String>;
  refill: number;
  expirationDate?: Date;
  dosage: String;
  patientId: string = '';
  isLoading: boolean = true;

  constructor(
    private medserve: MedicationService,
    private changeDetectorRef: ChangeDetectorRef,
    private route: ActivatedRoute,
    private router: RouterService,
    private ps: PatientserviceService,
    private snack: MatSnackBar
  ) {
    this.medications = [];
    this.placeholder = ['Details available through FDA'];
    this.refill = 0;
    this.dosage = '';
    this.patientId = this.route.snapshot.paramMap.get('patientId')!;
  }

  ngOnInit() {
    this.getallMeds();
  }

  getallMeds() {
    this.changeDetectorRef.detectChanges();
    this.medserve.viewMedications().subscribe((res) => {
      console.log(res);
      this.med = res;
      console.log(this.med);
      this.medications = this.med.results!;

      this.fixMeds();
    });
  }

  fixMeds() {
    //console.log(this.medications[0])
    for (let i = 0; i < this.medications.length; i++) {
      let res = this.medications[i];
      if (res.purpose === null) {
        res.purpose = this.placeholder;
      }
      if (res.inactive_ingredient === null) {
        res.inactive_ingredient = this.placeholder;
      }
      if (res.warnings === null) {
        res.warnings = this.placeholder;
      }
      if (res.indications_and_usage === null) {
        res.indications_and_usage = this.placeholder;
      }
      if (res.active_ingredient === null) {
        res.active_ingredient = this.placeholder;
      }
      if (res.dosage_and_administration === null) {
        res.dosage_and_administration = this.placeholder;
      }
    }

    this.isLoading = false;
  }

  addMed(med: Result) {
    if (med.dosage_and_administration != null) {
      this.dosage = med.dosage_and_administration[0];
    } else {
      this.dosage = 'Please contact your service provider for information';
    }

    if (this.expirationDate) {
      let newPrescription = new Prescription();

      newPrescription.dosage = this.dosage;
      newPrescription.refills = this.refill;
      newPrescription.doctorID = sessionStorage.getItem('userId') ?? '';
      newPrescription.expiration = this.expirationDate;

      const token = sessionStorage.getItem('bearerToken');
      const headers = new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: `Bearer ${token}`,
      });

      console.log('ABOUT TO POST');
      this.ps
        .addPrescription(headers, this.patientId, newPrescription)
        .subscribe((res) => {
          console.log(res);
          this.successsnack();
        }, (error) => {
          this.failsnack();
        });
    }
  }

  proceed() {
    this.router.openSPDash();
  }

  getPatientInfo() {
    // would be nice to display "Prescription Selection For <PATIENT>"
  }

  successsnack(){
    this.snack.open("Prescription Successfully Added","",
    {
      duration:2000
    })
  }

  failsnack(){
    this.snack.open("Failed to add prescription","",
        {
          duration:2000
        })
  }
}
