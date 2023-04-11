import { Component } from '@angular/core';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute } from '@angular/router';
import { firstValueFrom } from 'rxjs';
import { PostapptdialogcontentComponent } from 'src/app/booking/postapptdialogcontent/postapptdialogcontent.component';
import { Booking } from 'src/app/booking/model/Booking';
import { BookingdbService } from 'src/app/booking/service/bookingdb.service';
import { SelectmedsComponent } from 'src/app/doctorprescription/selectmeds/selectmeds.component';
import { RouterService } from 'src/app/healthroute/Service/router.service';
import { HealthrouteModule } from 'src/app/healthroute/healthroute.module';
import { MrService } from 'src/app/medicalrecord/mr.service';
import { PatientserviceService } from 'src/app/patient/patientservice.service';

@Component({
  selector: 'app-meeting-in-progress',
  templateUrl: './meeting-in-progress.component.html',
  styleUrls: ['./meeting-in-progress.component.css']
})
export class MeetingInProgressComponent {
  role ='';
  isProvider=false;
  bookingId:string="";
  booking: Booking = new Booking();
  constructor(private router:RouterService,private bookingserve:BookingdbService,private route: ActivatedRoute, private dialog: MatDialog, private medicalrecord: MrService, private snack: MatSnackBar, private ps: PatientserviceService){
    this.bookingId=this.route.snapshot.paramMap.get('bookingId')!;
    this.bookingserve.viewBooking(this.bookingId).subscribe(
      res=> {
        this.booking = res;
      }
    )
  }
  ngOnInit(): void {
    this.role = sessionStorage.getItem('Role')!;
    if(this.role==='ServiceProvider'){
      this.isProvider=true;
    }
  }
  completeMeeting(){
    if(this.isProvider){
      const dialogRef = this.dialog.open(PostapptdialogcontentComponent);

      dialogRef.afterClosed().subscribe(async result => {
        //console.log(`Form data: ${JSON.stringify(result)}`);
        // today's date initialized to midnight
        const todayDateTime = new Date();
        const todayDate = todayDateTime.toISOString().substring(0,10);

        const newRecord = {
          "doctorid": sessionStorage.getItem("userId") ?? "",
          "doctorName": sessionStorage.getItem("name") ?? "",
          "patientid": this.booking.patientId,
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

        // update booking to be completed
        this.booking.status = 3

        this.bookingserve.updateBooking(this.booking).subscribe(res => {
          console.log(res);
        })

        if (result.needsPrescription) {
          this.router.openCreatePrescription(this.booking.patientId);
        } else{
          this.router.openLanding();
        }
      })
    }
    else{
      this.router.openLanding();
    }
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
