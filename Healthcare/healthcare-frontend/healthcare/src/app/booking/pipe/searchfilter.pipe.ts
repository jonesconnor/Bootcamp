import { Pipe, PipeTransform } from '@angular/core';
import { ServiceProvider } from 'src/service-providers/models/serviceprovider';

@Pipe({
  name: 'searchfilter'
})
export class SearchfilterPipe implements PipeTransform {

  transform(providers: any, options: Array<String>, searchText: String): any {

    if(searchText.length==0 && options.length==0){
      return providers;
    }

    return providers.filter( (val:ServiceProvider)=>{
      return (val.firstName?.toLowerCase().includes(searchText.toLowerCase()) 
      || val.lastName?.toLowerCase().includes(searchText.toLowerCase()) 
      || val.location?.toLowerCase().includes(searchText.toLowerCase())
      || val.specialization?.toLowerCase().includes(searchText.toLowerCase()))
    })


  }

}
