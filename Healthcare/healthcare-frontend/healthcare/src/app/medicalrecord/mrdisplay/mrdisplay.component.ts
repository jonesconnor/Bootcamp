import { MrService } from './../mr.service';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MedicalRecord } from '../model/MedicalRecord';
import { MatExpansionPanel } from '@angular/material/expansion';
import { TruncatePipe } from '../truncate.pipe';
import { MatPaginator } from '@angular/material/paginator';
import { RouterService } from 'src/app/healthroute/Service/router.service';

@Component({
  selector: 'app-mrdisplay',
  templateUrl: './mrdisplay.component.html',
  styleUrls: ['./mrdisplay.component.css']
})
export class MrdisplayComponent implements OnInit {

  @ViewChild('notesPanel') notesPanel!: MatExpansionPanel;
  @ViewChild('notesTitle') notesTitle?: any;
  @ViewChild('paginator') paginator?: MatPaginator;

  dataSource: any
  records: Array<MedicalRecord> = [];
  expandedElement: any | null;
  selectedRecord: MedicalRecord | null = null;
  searchtext:String=""

  displayedColumns: string[] = ['date', 'doctorName', 'notes', 'review'];

  constructor(private recordhandler: MrService, private truncate: TruncatePipe, private router: RouterService) {}

  ngOnInit() {
    this.loadRecords();
  }

  loadRecords() {
    const patientid = sessionStorage.getItem('userId');
    this.recordhandler.getRecordsByPatient(patientid!).subscribe(
      (res:any) => {
        this.records = res;
        this.dataSource = this.records;
      },
      (err:any) => {
        console.log(err);
      }
    );
    this.recordhandler.medicalRecordList.subscribe((records: MedicalRecord[]) => {
      this.records = records;
      this.dataSource = this.records;
      if (this.paginator) {
        this.paginator.length = records.length;
        this.dataSource.paginator = this.paginator;
      }
    })
  }

  onNotesPanelChanged(record: MedicalRecord, panel: MatExpansionPanel) {
    const panelId = `panel-${record.id}`;
    if (record) {
      const panelElement = document.getElementById(panelId);
      const notesTitle = panelElement?.querySelector('.mat-expansion-panel-header-title')
      if (panel.expanded && notesTitle) {
        notesTitle.textContent = 'Notes';
      } else {
        if (record.notes && notesTitle) {
          const truncatedText = this.truncate.transform(record.notes, 50) as string;
          notesTitle.textContent = truncatedText;
        }
      }
    }
  }

  onLeaveReview(record: MedicalRecord) {
    this.router.openAddReview(record.doctorid!);
  }

}
