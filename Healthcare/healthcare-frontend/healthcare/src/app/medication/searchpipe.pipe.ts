import { Pipe, PipeTransform } from '@angular/core';
import { Result } from './model/result';

@Pipe({
  name: 'searchpipe'
})
export class SearchpipePipe implements PipeTransform {

  //found:Array<Result>=[]
  
  transform(medications: any, searchtext: any): any {

    if(searchtext==null){
      return medications;
    }
    console.log("inside pipe")
    console.log(medications)
    console.log(searchtext)
    searchtext = searchtext.toLowerCase()
   return medications.filter ((medobj:any) => {
    console.log(medobj.active_ingredient)
    const ingredient = medobj.active_ingredient[0].toLowerCase()
    const purp = medobj.purpose[0].toLowerCase()
    return ingredient.includes(searchtext) || purp.includes(searchtext)
   })
  }

}
