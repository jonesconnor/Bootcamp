import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CommonlibService {

  constructor() { }

  displayTax(role : string, salary: number){
    if (role === "manager") {
      return `tax is ${salary*10/100}`;
    } else if (role === "fresher") {
      return `tax is ${salary*5/100}`;
    } else {
      return `tax is ${salary*2/100}`;
    }
  }

  storeDetail(name: string) {
    alert(`hello ${name}`);
  }
}
