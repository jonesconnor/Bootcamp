import { Booking } from './booking';

export interface ServiceProvider {
  id?:string;
  firstName: string;
  lastName: string;
  location: string;
  email: string;
  phoneNumber: string;
  specialization: string;
  fee: number;
  averageRating: number;
  bookings: Booking[];
  notifications: string[];  
}
