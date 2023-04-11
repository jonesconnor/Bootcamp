import { Injectable } from '@angular/core';

@Injectable(
  {
  providedIn: 'root'
}
)
export class CommonlibService {

  constructor() { }

  displayHike (role : string, salary : number) : string
  {
 if (role==="manager")
   return "hike is  " + salary *10/100;
   else if (role==="fresher")
   return "hike is " + salary *5/100;
else
return "hike " + salary *2/100;
  }

  storeDetail(name : string)
  {
     alert("Hi , Welcome  " + name)
  }

}
