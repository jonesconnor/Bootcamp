export default class Payment {
  receiptId?: string;
  paymentToken?: string;
  serviceType?: string;
  hours?: number;
  pricePerHour?: number;
  status?: number = 0;
  payeeId?: string;
  payerId?: string;
}
