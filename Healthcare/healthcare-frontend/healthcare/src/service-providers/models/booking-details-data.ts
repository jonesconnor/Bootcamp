export interface BookingDetailsData {
  id?: string;
  patientName: string;
  bookingDate: string;
  bookingTime: string;
  duration: number;
  isEmergency: boolean;
  subject: string;
  location: string;
  status: string;
}

export enum DialogResult {
  update,
  confirm,
  close,
  start,
}
