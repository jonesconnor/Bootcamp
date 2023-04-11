import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { PostapptdialogcontentComponent } from '../postapptdialogcontent/postapptdialogcontent.component';
import { MrService } from 'src/app/medicalrecord/mr.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-postapptdialog',
  templateUrl: './postapptdialog.component.html',
  styleUrls: ['./postapptdialog.component.css']
})
export class PostapptdialogComponent {

  constructor(private dialog: MatDialog, private dialogRef: MatDialogRef<PostapptdialogcontentComponent>, private medicalrecord: MrService, private snack: MatSnackBar) {}

  openDialog() {
    const dialogRef = this.dialog.open(PostapptdialogcontentComponent);

    dialogRef.afterClosed().subscribe(result => {
      console.log(`Form data: ${JSON.stringify(result)}`);
      // today's date initialized to midnight
      const todayDateTime = new Date();
      const todayDate = todayDateTime.toISOString().substring(0,10);


      const newRecord = {
        "doctorid": sessionStorage.getItem("userId") ?? "",
        "doctorName": sessionStorage.getItem("name") ?? "",
        "patientid": "tbd",
        "notes": result.notes,
        "date": todayDate
      }

      this.medicalrecord.addRecord(newRecord).subscribe((res) => {
        console.log(`Record added: ${JSON.stringify(res)}`);
        this.successsnack();
      }, error => {
        console.error(`Error adding record: ${JSON.stringify(error)}`);
        this.failsnack();
      });

      if (result.needsPrescription) {
        // open doctor prescription dialog
      }
    })
  }

  successsnack(){
    this.snack.open("Record successfully created.","",
    {
      duration:2000
    })
  }

  failsnack(){
    this.snack.open("Failed to create Record","",
        {
          duration:2000
        })
  }

}
