import { ChangeDetectorRef, Component, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { Observable } from 'rxjs';
import { FulldialogComponent } from '../fulldialog/fulldialog.component';
import { MedicationService } from '../medication.service';
import { Medication } from '../model/medication';
import { Result } from '../model/result';

@Component({
  selector: 'app-medicationview',
  templateUrl: './medicationview.component.html',
  styleUrls: ['./medicationview.component.css'],
})
export class MedicationviewComponent {
  med: Medication = new Medication();
  medications: Array<Result>;
  placeholder: Array<String>;
  dataSource!: MatTableDataSource<any>;
  obs!: Observable<any>;
  @ViewChild(MatPaginator)
  paginator!: MatPaginator;
  searchtext: String = '';
  isLoading: boolean = true;

  constructor(
    private medserve: MedicationService,
    private changeDetectorRef: ChangeDetectorRef,
    private matdialogobj: MatDialog
  ) {
    this.medications = [];
    this.placeholder = ['Contact Service Provider for Missing Information'];
  }
  ngOnInit() {
    this.getallMeds();
    //this.fixMeds();
  }
  getallMeds() {
    this.changeDetectorRef.detectChanges();
    this.medserve.viewMedications().subscribe((res) => {
      console.log(res);
      this.med = res;
      console.log(this.med);
      this.medications = this.med.results!;
      //console.log(this.medications);
      this.dataSource = new MatTableDataSource<any>(this.medications);
      this.dataSource.paginator = this.paginator;
      this.obs = this.dataSource.connect();
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

  viewMore(me: Result) {
    this.matdialogobj.open(FulldialogComponent, {
      width: '50%',
      height: '50%',
      data: { result: me },
    });
  }
}
