import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'search'
})
export class SearchPipe implements PipeTransform {

  transform(records: any[], searchTerm: any): any[] {
    if (!records || !searchTerm) {
      return records;
    }

    return records.filter(record => {
      const dateMatch = record.date.toLowerCase().includes(searchTerm.toLowerCase());
      const doctorMatch = record.doctorName.toLowerCase().includes(searchTerm.toLowerCase());

      return dateMatch || doctorMatch
    });
  }

}
