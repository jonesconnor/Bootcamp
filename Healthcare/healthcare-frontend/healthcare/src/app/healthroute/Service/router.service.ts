import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root',
})
export class RouterService {
  constructor(private router: Router) {}

  openLogin() {
    this.router.navigate(['login']);
  }
  openInProgressMeeting(bookingId: string) {
    this.router.navigate(['meetinginprogress', bookingId]);
  }
  openPostAppt(bookingId: string) {
    this.router.navigate(['postapptdialog', { bookingId: bookingId }]);
  }
  openPatRegister() {
    this.router.navigate(['patientregister']);
  }
  openSPRegister() {
    this.router.navigate(['spregister']);
  }
  openLanding() {
    this.router.navigate(['landing']);
  }
  openPatientDash() {
    this.router.navigate(['patientdash']);
  }
  openSPDash() {
    this.router.navigate(['spdash']);
  }
  openAdminDash() {
    this.router.navigate(['admindash']);
  }
  openMeds() {
    this.router.navigate(['medications']);
  }
  openSearchProviders() {
    this.router.navigate(['providers']);
  }
  openPatientProfile() {
    this.router.navigate(['patientprofile']);
  }
  openMedicalHistory() {
    this.router.navigate(['medicalhistory']);
  }
  openReviews() {
    this.router.navigate(['reviews']);
  }
  // openViewRevieweeReviews() {
  //   this.router.navigate(['reviews/viewReview']);
  // }
  openCurrentReview(){
    this.router.navigate(['reviews/currentReview']);
  }
  openViewAllRevieweeReviews(spId:string) {
    this.router.navigate(['reviews/viewReview',{spId: spId}]);
  }
  openAddReview(spId:string){
    this.router.navigate(['reviews',{spId:spId}]);
  }
  openProviderProfile() {
    this.router.navigate(['provider/profile']);
  }
  openProviderCalendar() {
    this.router.navigate(['provider/schedule']);
  }

  openPaymentDashboard() {
    this.router.navigate(['payments/dashboard']);
  }

  openPayment(bookingId: string) {
    this.router.navigate([`payment/${bookingId}`]);
  }

  openUpdatebooking(bookingid: any) {
    this.router.navigate(['updatebooking', bookingid]);
  }

  openCreatePrescription(patientId: any) {
    this.router.navigate(['createprescription', patientId]);
  }

  openPrescription() {
    this.router.navigate(['prescription']);
  }
}
