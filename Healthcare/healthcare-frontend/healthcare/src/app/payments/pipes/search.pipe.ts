import { Pipe, PipeTransform } from '@angular/core';
import Payment from '../model/Payment';

@Pipe({
  name: 'search',
})
export class SearchPipe implements PipeTransform {
  transform(payments: any, searchText: String): any {
    if (searchText.length == 0) {
      return payments;
    }

    return payments.filter((val: Payment) => {
      return (
        val.payeeId!.toLowerCase().includes(searchText.toLowerCase()) ||
        val.payerId!.toLowerCase().includes(searchText.toLowerCase()) ||
        val.receiptId!.toLowerCase().includes(searchText.toLowerCase()) ||
        val.serviceType!.toLowerCase().includes(searchText.toLowerCase())
      );
    });
  }
}
