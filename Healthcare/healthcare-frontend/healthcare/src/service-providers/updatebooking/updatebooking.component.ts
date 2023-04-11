import { Component, Input } from '@angular/core';
import { Booking } from 'src/app/booking/model/Booking';
import { TimeSlot } from 'src/app/booking/model/TimeSlot';
import { FormGroup,  FormControl, } from '@angular/forms';
import { BookingdbService } from 'src/app/booking/service/bookingdb.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Booking as ProviderBooking } from 'src/service-providers/models/booking';
import { FormBuilder, NgForm, Validators } from '@angular/forms';
import { ServiceProvider } from '../models/serviceprovider';
import { ProvidersService } from 'src/service-providers/services/providers.service';
import { PatientserviceService } from 'src/app/patient/patientservice.service';
import { ActivatedRoute } from '@angular/router';
import { lastValueFrom } from 'rxjs';
import { HttpHeaders } from '@angular/common/http';
import { RouterService } from 'src/app/healthroute/Service/router.service';

@Component({
  selector: 'app-updatebooking',
  templateUrl: './updatebooking.component.html',
  styleUrls: ['./updatebooking.component.css'],
})
export class UpdatebookingComponent {
  bookingid!: String;
  oldbooking!: Booking;
  updatedBooking!: Booking;
  form: FormGroup;
  message = '';
  options = '';
  times = [
    '08:00',
    '08:30',
    '09:00',
    '09:30',
    '10:00',
    '10:30',
    '11:00',
    '11:30',
    '12:00',
    '12:30',
    '13:00',
    '13:30',
    '14:00',
    '14:30',
    '15:00',
    '15:30',
    '16:00',
    '16:30',
    '17:00',
    '17:30',
    '18:00',
    '18:30',
    '19:00',
    '19:30',
    '20:00',
    '20:30',
  ];
  check = 0;
  minDate!: Date;
  provider!: ServiceProvider;
  token = '';

  constructor(
    private route: ActivatedRoute,
    private formBuilder: FormBuilder,
    private serv: BookingdbService,
    private snackobj: MatSnackBar,
    private provservice: ProvidersService,
    private patservice: PatientserviceService,
    private router: RouterService,
  ) {

    this.form = this.formBuilder.group({
      subject: ["", [Validators.required, this.justSpaceValidator]],
      location: ["", [Validators.required, this.justSpaceValidator]],
      bookingDate: ["",[Validators.required],],
      bookingTime: ["",[Validators.required],],
      duration: ["", [Validators.required]],
    });

    this.route.paramMap.subscribe((params) => {
      if (!params.get('bookingId')) {
        return;
      }
      this.bookingid = params.get('bookingId')!;

      

      this.serv.viewBooking(this.bookingid).subscribe((res)=> {
        this.oldbooking = res
       // console.log(this.oldbooking)
        this.updatedBooking = this.oldbooking;
       // console.log(this.oldbooking.timeSlot?.bookingDate)
    
        this.form.setValue({
          subject: this.oldbooking.subject!, 
          location: this.oldbooking.location!, 
          bookingDate: new Date(this.oldbooking.timeSlot?.bookingDate!+"T00:00:00"), 
          bookingTime: this.oldbooking.timeSlot?.bookingTime?.substring(0,5),
          duration: this.oldbooking.timeSlot?.duration})
        
        //console.log(this.form.get('bookingDate'))
     })
     
      this.minDate = new Date();
    });
    this.provservice.getServiceProviderById( sessionStorage.getItem('userId')!).subscribe((res)=>{this.provider = res}) 
  }

  onSubmit() {
    this.updatedBooking.status = 1;
    this.updatedBooking.subject = this.form.get('subject')?.value;
    this.updatedBooking.location = this.form.get('location')?.value;
    this.updatedBooking.timeSlot!.bookingDate = this.form.get('bookingDate')?.value;
    this.updatedBooking.timeSlot!.bookingTime = this.form.get('bookingTime')?.value + ':00';
    this.updatedBooking.timeSlot!.duration = this.form.get('duration')?.value;

    //console.log(this.form.value);

    this.serv.updateBooking(this.updatedBooking).subscribe(
      (res) => {
        this.successsnack();
        this.clearForm();

        //this.updatePatient();
        

        console.log(this.updatedBooking.timeSlot!.bookingDate!+"")
        let ind = this.updatedBooking.timeSlot!.bookingTime!.lastIndexOf(":00")
        let readableTime = this.updatedBooking.timeSlot!.bookingTime?.substring(0,ind)
        ind = (this.updatedBooking.timeSlot!.bookingDate!+"").indexOf(" 00:00:00")
        let readableDate = (this.updatedBooking.timeSlot!.bookingDate!+"").substring(0,ind)
        let readableDateTime = readableDate+" "+readableTime
        
        console.log(readableDate)
        console.log(readableDateTime)

        let formattedDate = this.getFormatDate(readableDate!, this.updatedBooking.timeSlot!.bookingTime!)
        let newBooking : ProviderBooking = { bookingId: this.updatedBooking.id, bookingDate: formattedDate, duration: this.updatedBooking.timeSlot!.duration!}
        console.log(newBooking)
        this.provider.bookings.push(newBooking)
        this.provservice.updateBookingForProvider(this.provider.id!,this.updatedBooking.id!,newBooking ).subscribe()

        const jwtToken = sessionStorage.getItem('bearerToken');
        this.patservice
          .addNotification(
              new HttpHeaders().set('Authorization', `Bearer ${jwtToken}`),
              this.updatedBooking.patientId!,
              'Details for appointment with '+this.provider.lastName+' on ' + readableDateTime+ ' has been updated. Please review changes.'
            ).subscribe();

        this.router.openSPDash();

      },
      (err) => {
        this.failsnack();
      }
    );
  }

  getFormatDate(dateString: string, timeString: string) :Date{
    let formattedDate = new Date(dateString)
    //formattedDate.setDate(formattedDate.getDate()+1)
    formattedDate.setHours(parseInt(timeString.substring(0,2)))
    formattedDate.setMinutes(parseInt(timeString.substring(3,5)))
    return formattedDate
  }

  public justSpaceValidator(control: FormControl) {
    const isWhitespace = (control.value || '').trim().length === 0;
    const isValid = !isWhitespace;
    return isValid ? null : { 'whitespace': true };
}

/*   updatePatient(){
    if (this.provider.id != undefined) {
      let ind = this.updatedBooking.timeSlot!.bookingTime!.lastIndexOf(":00")
      let readableDate = this.updatedBooking.timeSlot!.bookingDate!+" "+this.updatedBooking.timeSlot!.bookingTime?.substring(0,ind)
      console.log(readableDate)
      const jwtToken = sessionStorage.getItem('bearerToken');
      this.patservice
        .addNotification(
          new HttpHeaders().set('Authorization', `Bearer ${jwtToken}`),
          this.updatedBooking.patientId!,
          'Appointment with ' +
          this.provider.lastName +
          ' on ' +
          readableDate +
          ' has been updated.'
        ).subscribe();
    }
  } */

  clearForm() {
    this.form.reset();
  }

  myFilter = (d: Date | null): boolean => {
    const day = (d || new Date())
    // Prevent past days to be selected
    
    if(this.provider==undefined || this.provider==null || this.provider.bookings==undefined || this.provider.bookings==null ){
      return day>this.minDate
    }
    let alreadyBooked = this.provider.bookings.map((b)=> 
    {let temp = new Date(b.bookingDate); 
      return new Date(temp.getFullYear(), temp.getMonth(), temp.getDate(),0,0,0,0).toDateString()})
      return (day>this.minDate && !alreadyBooked.includes(day.toDateString())) ||  (day.toISOString().substring(0,10)==this.oldbooking.timeSlot!.bookingDate!)
  };

  handleUndo() {
    this.updatedBooking = this.oldbooking;
    this.router.openSPDash();
  }

  successsnack() {
    this.snackobj.open('Booking successfully updated', '', {
      duration: 2000,
    });
  }

  failsnack() {
    this.snackobj.open(
      'Failed to update booking. Please check all necessary information is correct',
      '',
      {
        duration: 2000,
      }
    );
  }
}
