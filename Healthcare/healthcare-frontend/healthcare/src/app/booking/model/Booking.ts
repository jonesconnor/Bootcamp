import { TimeSlot } from './TimeSlot';

export class Booking {
  id?: string;
  patientId?: string;
  isEmergency?: boolean;
  serviceProviderId?: string;
  subject?: string;
  paymentToken?: string;
  timeSlot?: TimeSlot;
  location?: string;
  status?: number;
  recurrenceType?: string;
  recurrenceInterval?: number;
  recurrenceEndDate?: string;
}
