import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'search'
})

// Implement logic to filter the given contacts based on given searchText
// Convert text to lowercase
export class SearchPipe implements PipeTransform {

  transform(contacts: any, searchText: any): any {
    if (!contacts || !searchText) {
      return contacts;
    }

    searchText = searchText.toLowerCase();

    return contacts.filter(contact => {
      const name = contact.name.toLowerCase();
      const mobile = contact.mobile.toString();

      return name.includes(searchText) || mobile.includes(searchText);
    });
  }


}
