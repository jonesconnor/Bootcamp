import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'paymentStatus',
})
export class PaymentStatusPipe implements PipeTransform {
  transform(value: number, ...args: unknown[]): unknown {
    switch (value) {
      case 2:
        return 'CONFIRMED';
      case 1:
        return 'CANCELLED';
      case 0:
        return 'PENDING';
      default:
        return 'PENDING';
    }
  }
}
